package sparta.cafeteria.menu.application.out;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PageCommand {
    int page;
    int size;
    int limit;
}
