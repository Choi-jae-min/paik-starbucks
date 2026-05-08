package sparta.cafeteria.order.application.port.out;


import sparta.cafeteria.order.domain.Order;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository {
    Order saveOrder(Order order);
    List<Long> findTop3MenuIdsSince(LocalDateTime from);
}