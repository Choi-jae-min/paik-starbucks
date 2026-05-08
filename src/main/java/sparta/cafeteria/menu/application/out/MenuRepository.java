package sparta.cafeteria.menu.application.out;

import sparta.cafeteria.menu.domain.Menu;

import java.util.List;

public interface MenuRepository {
    void saveMenu(Menu menu);

    Menu findMenu(Long id);

    List<Menu> findMenuList(PageCommand pageCommand);
}
