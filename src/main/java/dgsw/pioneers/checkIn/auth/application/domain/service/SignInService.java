package dgsw.pioneers.checkIn.auth.application.domain.service;

import dgsw.pioneers.checkIn.auth.application.domain.exception.PasswordMatchException;
import dgsw.pioneers.checkIn.auth.application.domain.model.Token;
import dgsw.pioneers.checkIn.auth.application.port.in.SignInCommand;
import dgsw.pioneers.checkIn.auth.application.port.in.SignInUseCase;
import dgsw.pioneers.checkIn.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.member.application.domain.model.MemberRole;
import dgsw.pioneers.checkIn.member.application.port.out.LoadMemberPort;
import dgsw.pioneers.checkIn.member.global.annotation.UseCase;
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
    public Token signIn(SignInCommand signInCommand) {

        Member member = loadMemberPort.loadMember(signInCommand.getMemberId());

        if (!checkPw(signInCommand.getPw(), member.getPw())) {
            throw new PasswordMatchException();
        } else {
            return createToken(signInCommand.getMemberId().getValue(), member.getMemberRole());
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
