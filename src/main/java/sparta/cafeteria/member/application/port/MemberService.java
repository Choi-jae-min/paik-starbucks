package sparta.cafeteria.member.application.port;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sparta.cafeteria.member.application.port.in.MemberUseCase;
import sparta.cafeteria.member.application.port.in.RegisterMemberCommand;
import sparta.cafeteria.member.application.port.out.MemberRepository;
import sparta.cafeteria.member.domain.Member;

@Service
@RequiredArgsConstructor
public class MemberService implements MemberUseCase {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member getMember(Long id) {
        return memberRepository.getMemberById(id);
    }

    @Override
    public Member signUp(RegisterMemberCommand command) {
        Member.checkPassword(command.getPassword());

        Member member = Member.register(
                command.getEmail(),
                command.getName(),
                passwordEncoder.encode(command.getPassword())
        );
        memberRepository.saveMember(member);
        return member;
    }
}
