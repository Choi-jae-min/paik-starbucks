package sparta.cafeteria.member.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RegisterMemberCommand {
    String email;
    String password;
    String name;
}
