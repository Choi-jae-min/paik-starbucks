package sparta.cafeteria.order.infra.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sparta.cafeteria.order.application.port.in.OrderUseCase;
import sparta.cafeteria.order.domain.Order;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderUseCase orderUseCase;

    @PostMapping
    public ResponseEntity<Order> placeOrder(
            @RequestParam Long memberId,
            @RequestParam Long menuId,
            @RequestParam(defaultValue = "1") Long quantity
    ) {
        Order order = orderUseCase.placeOrder(memberId, menuId, quantity);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
