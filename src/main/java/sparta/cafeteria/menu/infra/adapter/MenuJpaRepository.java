package sparta.cafeteria.menu.infra.adapter;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuJpaRepository extends JpaRepository<MenuJpaEntity, Long> {
}
