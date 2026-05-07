package sparta.cafeteria.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Member {
    private final Long id;
    private final String email;
    private final String name;
    private String password;
    private MemberStatus status;

    public Member(Long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.status = MemberStatus.ACTIVE;
    }

    public static Member register(String email, String name, String password) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("유효하지 않은 이메일입니다");
        }
        return new Member(null, email, name, password, MemberStatus.ACTIVE);
    }

    public static void checkPassword(String password) {
        if (password == null || password.length() < 8) {
            throw new IllegalArgumentException("비밀번호는 8자 이상이어야 합니다");
        }
    }
}
