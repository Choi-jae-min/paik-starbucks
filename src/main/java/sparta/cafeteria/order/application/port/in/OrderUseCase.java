package sparta.cafeteria.order.application.port.in;


import sparta.cafeteria.order.domain.Order;

public interface OrderUseCase {
    Order placeOrder(Long memberId, Long menuId, Long quantity);
}
