package sparta.cafeteria.menu.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.cafeteria.menu.application.in.MenuUseCase;
import sparta.cafeteria.menu.application.out.MenuRepository;
import sparta.cafeteria.menu.application.in.RegisterMenuCommand;
import sparta.cafeteria.menu.application.out.PageCommand;
import sparta.cafeteria.menu.application.out.PopularMenu;
import sparta.cafeteria.menu.domain.Menu;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService implements MenuUseCase {
    private final MenuRepository menuRepository;
    private final PopularMenu popularMenu;

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
        List<Long> popularMenuIds = popularMenu.getTop3MenuIds();
        if (popularMenuIds.isEmpty()) return List.of();
        return menuRepository.findMenusByIds(popularMenuIds);
    }
}
