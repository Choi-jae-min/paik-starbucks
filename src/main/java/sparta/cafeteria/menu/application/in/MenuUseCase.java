package sparta.cafeteria.menu.application.in;

import sparta.cafeteria.menu.domain.Menu;

import java.util.List;

public interface MenuUseCase {
    Menu registerMenu(RegisterMenuCommand registerMenuCommand);

    Menu getMenu(Long id);

    List<Menu> getMenuList();
}
