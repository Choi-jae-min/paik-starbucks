package sparta.cafeteria.menu.domain;

import lombok.Getter;

@Getter
public class Menu {
    private Long id;

    private String name;

    private Long price;

    private MenuStatus menuStatus;

    public Menu(Long id, String name, Long price, MenuStatus menuStatus) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.menuStatus = menuStatus;
    }

    public static Menu registerMenu(String name, Long price, MenuStatus menuStatus){
        if(price <= 0 ){
            throw new IllegalArgumentException("0원보다 작을 수 없습니다.");
        }
        return new Menu(null, name, price, menuStatus);
    }
}
