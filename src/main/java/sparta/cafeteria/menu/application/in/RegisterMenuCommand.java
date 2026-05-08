package sparta.cafeteria.menu.application.in;

import lombok.AllArgsConstructor;
import lombok.Getter;
import sparta.cafeteria.menu.domain.MenuStatus;

@AllArgsConstructor
@Getter
public class RegisterMenuCommand {
    private String name;
    private Long price;
    private MenuStatus menuStatus;
}
