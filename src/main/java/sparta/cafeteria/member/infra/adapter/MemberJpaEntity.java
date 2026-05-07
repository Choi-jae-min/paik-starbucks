package sparta.cafeteria.member.infra.adapter;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.cafeteria.member.domain.Member;
import sparta.cafeteria.member.domain.MemberStatus;

@Entity(name = "members")
@Table
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    private String password;

    @Enumerated(EnumType.STRING)
    private MemberStatus status;

    public Member toDomain() {
        return new Member(id, email, name);
    }

    public static MemberJpaEntity fromDomain(Member member) {
        MemberJpaEntity entity = new MemberJpaEntity();
        entity.id = member.getId();
        entity.email = member.getEmail();
        entity.name = member.getName();
        entity.password = member.getPassword();
        entity.status = member.getStatus();
        return entity;
    }
}
