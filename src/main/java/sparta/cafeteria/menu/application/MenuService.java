package sparta.cafeteria.menu.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.cafeteria.menu.application.in.MenuUseCase;
import sparta.cafeteria.menu.application.out.MenuRepository;
import sparta.cafeteria.menu.application.in.RegisterMenuCommand;
import sparta.cafeteria.menu.application.out.PageCommand;
import sparta.cafeteria.menu.domain.Menu;
import sparta.cafeteria.order.application.port.out.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService implements MenuUseCase {
    private final MenuRepository menuRepository;
    private final OrderRepository orderRepository;

    @Override
    public Menu registerMenu(RegisterMenuCommand registerMenuCommand){
        Menu menu = Menu.registerMenu(registerMenuCommand.getName(),registerMenuCommand.getPrice(), registerMenuCommand.getMenuStatus());

        menuRepository.saveMenu(menu);
        return menu;
    }

    @Override
    public Menu getMenu(Long id) {
        return null;
    }

    @Override
    public List<Menu> getMenuList(PageCommand pageCommand) {
        return menuRepository.findMenuList(pageCommand);
    }

    @Override
    public List<Menu> getPopularMenus() {
        LocalDateTime from = LocalDateTime.now().minusDays(7);
        List<Long> popularMenuIds = orderRepository.findTop3MenuIdsSince(from);
        return menuRepository.findMenusByIds(popularMenuIds);
    }
}
