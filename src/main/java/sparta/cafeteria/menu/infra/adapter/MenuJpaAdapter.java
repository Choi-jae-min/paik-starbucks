package sparta.cafeteria.menu.infra.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @Transactional(readOnly = true)
    public List<Menu> findMenuList(PageCommand pageCommand) {
        Pageable pageable = PageRequest.of(pageCommand.getPage(), pageCommand.getSize());
        return menuJpaRepository.findAll(pageable).stream()
                .map(MenuJpaEntity::toDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Menu> findMenusByIds(List<Long> ids) {
        return menuJpaRepository.findAllById(ids).stream()
                .map(MenuJpaEntity::toDomain)
                .toList();
    }
}
