package sparta.cafeteria.member.infra.api.dto;

import jakarta.validation.constraints.NotBlank;
import sparta.cafeteria.member.application.port.in.RegisterMemberCommand;

public record MemberCreateRequest(
        @NotBlank
        String email,

        @NotBlank
        String password,

        @NotBlank
        String name
) {
    public RegisterMemberCommand toCommand() {
        return new RegisterMemberCommand(email, password, name);
    }
}
