package sparta.cafeteria.menu.application.out;

import java.util.List;

public interface PopularMenu {
    void incrementMenuScore(Long menuId);
    List<Long> getTop3MenuIds();
}
