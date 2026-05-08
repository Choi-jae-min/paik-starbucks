package sparta.cafeteria.member.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChargePointCommand {
    private Long memberId;
    private Long amount;
}