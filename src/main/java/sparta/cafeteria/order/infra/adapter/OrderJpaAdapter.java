package sparta.cafeteria.order.infra.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sparta.cafeteria.order.application.port.out.OrderRepository;
import sparta.cafeteria.order.domain.Order;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderJpaAdapter implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;

    @Override
    @Transactional
    public Order saveOrder(Order order) {
        OrderJpaEntity entity = OrderJpaEntity.from(order);
        return orderJpaRepository.save(entity).toDomain();
    }

    @Override
    public List<Long> findTop3MenuIdsSince(LocalDateTime from) {
        return orderJpaRepository.findTop3MenuIdsSince(from)
                .stream()
                .map(row -> (Long) row[0])
                .toList();
    }
}