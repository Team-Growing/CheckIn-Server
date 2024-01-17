package dgsw.pioneers.checkIn.domain.member.application.domain.service;

import dgsw.pioneers.checkIn.global.annotation.UseCase;
import dgsw.pioneers.checkIn.global.exception.custom.ParameterNotFoundException;
import dgsw.pioneers.checkIn.domain.member.application.domain.exception.MemberDuplicateException;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member.MemberId;
import dgsw.pioneers.checkIn.domain.member.application.port.in.SignUpUseCase;
import dgsw.pioneers.checkIn.domain.member.application.port.out.CreateMemberPort;
import dgsw.pioneers.checkIn.domain.member.application.port.out.ExistMemberPort;
import dgsw.pioneers.checkIn.global.lib.zonedatetime.ZoneDateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SignUpService implements SignUpUseCase {

    private final ExistMemberPort existMemberPort;
    private final CreateMemberPort createMemberPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void signUpTeacher(Member member) {

        checkExistMember(member.getMemberId());
        member.encodePw(passwordEncoder.encode(member.getPw()));

        createMemberPort.createMember(member);
    }

    @Override
    @Transactional
    public void signUpStudent(Member member) {

        checkExistMember(member.getMemberId());
        member.encodePw(passwordEncoder.encode(member.getPw()));

        member.modifyInfoYear(ZoneDateTimeUtil.nowToLocalDate().getYear());

        createMemberPort.createMember(member);
    }

    private void checkExistMember(MemberId signUpCommand) {
        if (existMemberPort.existByMemberId(signUpCommand)) {
            throw new MemberDuplicateException();
        }
    }
}
