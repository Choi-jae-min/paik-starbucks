package sparta.cafeteria.member.application.port.in;

import sparta.cafeteria.member.domain.Member;

public interface MemberUseCase {
    Member getMember(Long id);
    Member signUp(RegisterMemberCommand command);
}
