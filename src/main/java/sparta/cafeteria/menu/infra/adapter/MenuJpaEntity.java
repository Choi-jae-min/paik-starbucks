package sparta.cafeteria.menu.infra.adapter;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.cafeteria.menu.domain.Menu;
import sparta.cafeteria.menu.domain.MenuStatus;

@Entity
@Table(name = "menus")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    @Enumerated(EnumType.STRING)
    private MenuStatus menuStatus;

    public Menu toDomain() {
        return new Menu(id, name, price, menuStatus);
    }

    public static MenuJpaEntity from(Menu menu) {
        MenuJpaEntity menuJpaEntity = new MenuJpaEntity();
        menuJpaEntity.id = menu.getId();
        menuJpaEntity.name = menu.getName();
        menuJpaEntity.price = menu.getPrice();
        menuJpaEntity.menuStatus = menu.getMenuStatus();
        return menuJpaEntity;
    }
}
