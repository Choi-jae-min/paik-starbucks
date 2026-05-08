package sparta.cafeteria.member.infra.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import sparta.cafeteria.member.application.port.in.ChargePointCommand;

public record ChargePointRequest(
        @NotNull
        Long memberId,

        @NotNull
        @Positive
        Long amount
) {
    public ChargePointCommand toCommand() {
        return new ChargePointCommand(memberId, amount);
    }
}