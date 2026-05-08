package sparta.cafeteria.order.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Order {
    private Long id;
    private Long memberId;
    private Long menuId;
    private Long price;
    private Long quantity;
    private LocalDateTime orderedAt;

    public Order(Long id, Long memberId, Long menuId, Long price,Long quantity, LocalDateTime orderedAt) {
        this.id = id;
        this.memberId = memberId;
        this.menuId = menuId;
        this.price = price;
        this.quantity = quantity;
        this.orderedAt = orderedAt;
    }

    public static Order place(Long memberId, Long menuId, Long price,Long quantity) {
        return new Order(null, memberId, menuId, price, quantity, LocalDateTime.now());
    }
}