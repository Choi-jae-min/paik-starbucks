package sparta.cafeteria.order.infra.adapter;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.cafeteria.order.domain.Order;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long memberId;
    private Long menuId;
    private Long price;
    private Long quantity;
    private LocalDateTime orderedAt;

    public static OrderJpaEntity from(Order order) {
        OrderJpaEntity entity = new OrderJpaEntity();
        entity.memberId = order.getMemberId();
        entity.menuId = order.getMenuId();
        entity.price = order.getPrice();
        entity.quantity = order.getQuantity();
        entity.orderedAt = order.getOrderedAt();
        return entity;
    }

    public Order toDomain() {
        return new Order(id, memberId, menuId, price,quantity, orderedAt);
    }
}
