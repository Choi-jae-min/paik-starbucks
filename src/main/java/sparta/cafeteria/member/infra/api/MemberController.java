package sparta.cafeteria.member.infra.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sparta.cafeteria.member.application.port.in.MemberUseCase;
import sparta.cafeteria.member.domain.Member;
import sparta.cafeteria.member.infra.api.dto.MemberCreateRequest;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberUseCase memberUseCase;

    @GetMapping("/{id}")
    public Member getMember(@PathVariable Long id) {
        return memberUseCase.getMember(id);
    }

    @PostMapping("/auth/signUp")
    public Member saveMember(@RequestBody @Valid MemberCreateRequest request) {
        return memberUseCase.signUp(request.toCommand());
    }
}
