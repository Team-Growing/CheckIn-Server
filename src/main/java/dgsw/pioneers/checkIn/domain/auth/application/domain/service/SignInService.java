package dgsw.pioneers.checkIn.domain.auth.application.domain.service;

import dgsw.pioneers.checkIn.domain.auth.adapter.in.web.dto.req.SignInReq;
import dgsw.pioneers.checkIn.domain.auth.application.domain.exception.PasswordMatchException;
import dgsw.pioneers.checkIn.domain.auth.application.domain.model.Token;
import dgsw.pioneers.checkIn.domain.auth.application.port.in.SignInUseCase;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole;
import dgsw.pioneers.checkIn.domain.member.application.port.out.LoadMemberPort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SignInService implements SignInUseCase {

    private final LoadMemberPort loadMemberPort;
    private final PasswordEncoder passwordEncoder;
    private final TokenGenerateService tokenGenerateService;

    @Override
    public Token signIn(SignInReq signInReq) {

        Member.MemberId memberId = new Member.MemberId(signInReq.getId());

        Member member = loadMemberPort.loadMember(memberId);

        if (!checkPw(signInReq.getPw(), member.getPw())) {
            throw new PasswordMatchException();
        } else {
            return createToken(memberId.getValue(), member.getMemberRole());
        }
    }

    private boolean checkPw(String verifyPw, String originPw) {
        return passwordEncoder.matches(verifyPw, originPw);
    }

    private Token createToken(String memberId, @NotNull MemberRole role) {
        return Token.builder()
                .accessToken(tokenGenerateService.generateAccessToken(memberId, role))
                .refreshToken(tokenGenerateService.generateRefreshToken(memberId, role))
                .build();
    }
}
