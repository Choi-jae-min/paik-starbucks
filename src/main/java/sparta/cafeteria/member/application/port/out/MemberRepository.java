package sparta.cafeteria.member.application.port.out;

import sparta.cafeteria.member.domain.Member;

public interface MemberRepository {
    Member getMemberById(Long id);
    void saveMember(Member member);
}
