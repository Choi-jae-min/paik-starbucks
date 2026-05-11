# paik-starbucks
백스타벅스-개인과제


## ERD
<img width="489" height="467" alt="image" src="https://github.com/user-attachments/assets/67234ba5-c8cb-4864-903d-bc115c24fd3c" />

## 패키지 구조 요약
 
```
sparta.cafeteria
├── member
│   ├── domain          ← 순수 도메인 (Member, MemberStatus)
│   ├── application
│   │   └── port
│   │       ├── in      ← UseCase, Command (입력 포트)
│   │       └── out     ← Repository interface (출력 포트)
│   └── infra
│       ├── adapter     ← JPA Entity, JpaAdapter (DB 어댑터)
│       └── api         ← Controller, DTO (HTTP 어댑터)
├── menu
│   └── (동일 구조)
└── order
    └── (동일 구조)
```
 
헥사고날 아키텍처의 핵심인 **"의존성이 항상 외부 → 내부 방향"** 을 패키지 구조로 강제합니다.
`infra`는 `application`을 알지만, `domain`은 `infra`를 전혀 모릅니다.

### 설계의도 헥사고날 아키텍처 적용 이유

이 프로젝트는 다음 프로젝트를 위한 **헥사고날 아키텍처 학습**을 목표로 설계되었습니다.
 
핵심 의도는 **"비즈니스 로직을 외부 기술로부터 분리"** 입니다.
 
```
[외부] HTTP / JPA / Redis       ← infra 레이어
          ↕ Port(Interface)
[내부] 비즈니스 로직 Domain      ← application / domain 레이어
```
 
- `Domain` (`Member`, `Menu`, `Order`) 은 순수 Java 객체로, Spring이나 JPA에 의존하지 않습니다.
- `Port`(interface)를 통해 내부와 외부를 연결하므로, 나중에 MySQL → MongoDB로 바꾸더라도 `infra`만 교체하면 됩니다.
- `Application Service`는 Port에만 의존하기 때문에 단위 테스트 작성이 쉬워집니다.

학습 내용 정리: [Hexagonal Architecture 노션](https://long-holly-6cf.notion.site/Hexagonal-Architecture-35357c64749980a99008d7daf96fddb8?pvs=74)

## 문제 해결 전략 및 분석
 
### 주문 시 포인트 차감 정합성
 
**문제**: 포인트 차감과 주문 저장이 따로 실패하면 데이터 불일치 발생
 
**분석**: 두 작업이 원자적으로 처리되어야 함
 
**해결**: `OrderService.placeOrder()`에 `@Transactional` 적용하여 포인트 차감 → 주문 저장을 하나의 트랜잭션으로 묶음
 
```java
@Transactional
public Order placeOrder(Long memberId, Long menuId, Long quantity) {
    member.deductPoint(totalPrice);   // 포인트 차감
    memberRepository.saveMember(member);
    Order saved = orderRepository.saveOrder(order);  // 주문 저장
    dataPlatformPort.send(saved);     // 실시간 전송
    return saved;
}
```
 
---
 
### 인기 메뉴 조회 정확성
 
**문제**: 최근 7일간 메뉴별 주문 횟수를 정확하게 집계해야 함
 
**분석**: 단순 조회로는 집계 불가 → DB 레벨 GROUP BY + COUNT 필요
 
**해결**: JPQL로 `ordered_at >= :from` 조건 + `GROUP BY menuId` + `ORDER BY COUNT DESC LIMIT 3` 쿼리 직접 작성
 
```java
@Query("SELECT o.menuId, COUNT(o) FROM OrderJpaEntity o " +
       "WHERE o.orderedAt >= :from " +
       "GROUP BY o.menuId ORDER BY COUNT(o) DESC LIMIT 3")
List<Object[]> findTop3MenuIdsSince(LocalDateTime from);
```
 
집계 결과 ID 목록 → `findMenusByIds()`로 메뉴 상세 정보 조회하는 2-step 방식으로 도메인 순수성 유지.
 
---
 
### 데이터 플랫폼 실시간 전송
 
**문제**: 주문 완료 후 외부 플랫폼으로 전송 필요. 전송 실패가 주문에 영향을 줘서는 안 됨.
 
**분석**: 전송 로직이 OrderService에 직접 있으면 외부 의존성이 도메인에 침투함
 
**해결**: `DataPlatformPort` 인터페이스로 추상화, `DataPlatformClient`(Mock 구현체)로 현재는 로깅 처리. 실제 외부 API 연동 시 구현체만 교체.
 
```java
// Port (내부 계약)
public interface DataPlatformPort {
    void send(Order order);
}
 
// Adapter (외부 구현 - 현재 Mock)
@Component
public class DataPlatformClient implements DataPlatformPort {
    public void send(Order order) {
        log.info("[DataPlatform] memberId={}, menuId={}, price={}",
                order.getMemberId(), order.getMenuId(), order.getPrice());
    }
}
```
