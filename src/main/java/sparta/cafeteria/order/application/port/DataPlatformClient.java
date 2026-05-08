package sparta.cafeteria.order.application.port;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sparta.cafeteria.order.application.port.out.DataPlatformPort;
import sparta.cafeteria.order.domain.Order;

@Slf4j
@Component
public class DataPlatformClient implements DataPlatformPort {

    @Override
    public void send(Order order) {
        log.info("[DataPlatform] memberId={}, menuId={}, price={}",
                order.getMemberId(), order.getMenuId(), order.getPrice());
    }
}