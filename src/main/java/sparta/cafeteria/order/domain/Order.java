package sparta.cafeteria.order.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Order {
    private final Long id;
    private final Long memberId;
    private final Long menuId;
    private final Long price;
    private final Long quantity;
    private final LocalDateTime orderedAt;

    public static Order place(Long memberId, Long menuId, Long price,Long quantity) {
        return new Order(null, memberId, menuId, price, quantity, LocalDateTime.now());
    }
}