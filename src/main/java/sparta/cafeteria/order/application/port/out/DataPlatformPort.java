package sparta.cafeteria.order.application.port.out;

import sparta.cafeteria.order.domain.Order;

public interface DataPlatformPort {
    void send(Order order);
}