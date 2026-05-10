package sparta.cafeteria.menu.infra.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.cafeteria.menu.application.in.MenuUseCase;
import sparta.cafeteria.menu.application.out.PageCommand;
import sparta.cafeteria.menu.domain.Menu;
import sparta.cafeteria.menu.infra.api.dto.CreateMenuDto;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Menu>> getMenuList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        PageCommand pageCommand = new PageCommand(page, size);
        return ResponseEntity.ok(menuUseCase.getMenuList(pageCommand));
    }

    @GetMapping("/popular")
    public ResponseEntity<List<Menu>> getPopularMenus() {
        return ResponseEntity.ok(menuUseCase.getPopularMenus());
    }
}