package sparta.cafeteria.member.infra.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sparta.cafeteria.member.application.port.out.MemberRepository;
import sparta.cafeteria.member.domain.Member;

@Repository
@RequiredArgsConstructor
public class MemberJpaAdapter implements MemberRepository {
    private final MemberJpaRepository memberJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public Member getMemberById(Long id) {
        MemberJpaEntity memberJpaEntity = memberJpaRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Member not found")
        );
        return memberJpaEntity.toDomain();
    }

    @Override
    @Transactional
    public void saveMember(Member member) {
        MemberJpaEntity memberJpaEntity = MemberJpaEntity.fromDomain(member);
        memberJpaRepository.save(memberJpaEntity);

        memberJpaEntity.toDomain();
    }
}
