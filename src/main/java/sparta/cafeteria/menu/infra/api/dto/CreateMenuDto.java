package sparta.cafeteria.menu.infra.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import sparta.cafeteria.menu.application.in.RegisterMenuCommand;
import sparta.cafeteria.menu.domain.MenuStatus;

public record CreateMenuDto (
        @NotBlank
        String name,
        @NotNull
        Long price,
        @NotBlank
        String status
){
        public RegisterMenuCommand toCommand() {
                try {
                        MenuStatus menuStatus = MenuStatus.valueOf(status.toUpperCase());
                        return new RegisterMenuCommand(name,price, menuStatus);
                } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException("잘못된 상태 저장: " + status);
                }
        }
}
