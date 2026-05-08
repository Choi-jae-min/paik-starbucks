package sparta.cafeteria.menu.infra.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sparta.cafeteria.menu.application.out.MenuRepository;
import sparta.cafeteria.menu.application.out.PageCommand;
import sparta.cafeteria.menu.domain.Menu;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MenuJpaAdapter implements MenuRepository {
    private final MenuJpaRepository menuJpaRepository;

    @Override
    @Transactional
    public void saveMenu(Menu menu) {
        MenuJpaEntity entity = MenuJpaEntity.from(menu);
        menuJpaRepository.save(entity);
    }

    @Override
    public Menu findMenu(Long id) {
        return null;
    }

    @Override
    public List<Menu> findMenuList(PageCommand pageCommand) {
        return List.of();
    }
}
