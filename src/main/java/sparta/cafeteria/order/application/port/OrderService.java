package sparta.cafeteria.order.application.port;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.cafeteria.member.application.port.out.MemberRepository;
import sparta.cafeteria.member.domain.Member;
import sparta.cafeteria.menu.application.out.MenuRepository;
import sparta.cafeteria.menu.domain.Menu;
import sparta.cafeteria.order.application.port.in.OrderUseCase;
import sparta.cafeteria.order.application.port.out.DataPlatformPort;
import sparta.cafeteria.order.application.port.out.OrderRepository;
import sparta.cafeteria.order.domain.Order;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderUseCase {

    private final MemberRepository memberRepository;
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;
    private final DataPlatformPort dataPlatformPort;

    @Override
    @Transactional
    public Order placeOrder(Long memberId, Long menuId, Long quantity) {
        Member member = memberRepository.getMemberById(memberId);
        Menu menu = menuRepository.findMenu(menuId);

        long totalPrice = menu.getPrice() * quantity;
        member.deductPoint(totalPrice);
        memberRepository.saveMember(member);

        Order order = Order.place(memberId, menuId, totalPrice, quantity);
        Order saved = orderRepository.saveOrder(order);
        dataPlatformPort.send(saved);

        return saved;
    }
}