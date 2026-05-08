package sparta.cafeteria.menu.infra.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sparta.cafeteria.menu.application.in.MenuUseCase;
import sparta.cafeteria.menu.domain.Menu;
import sparta.cafeteria.menu.infra.api.dto.CreateMenuDto;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
public class MenuController {
    private final MenuUseCase menuUseCase;

    @PostMapping()
    public ResponseEntity<Menu> addMenu(@RequestBody @Valid CreateMenuDto createMenuDto) {
        Menu menu = menuUseCase.registerMenu(createMenuDto.toCommand());

        return new ResponseEntity<>(menu, HttpStatus.CREATED);
    }
}
