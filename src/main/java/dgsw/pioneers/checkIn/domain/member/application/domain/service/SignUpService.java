package dgsw.pioneers.checkIn.domain.member.application.domain.service;

import dgsw.pioneers.checkIn.global.annotation.UseCase;
import dgsw.pioneers.checkIn.global.exception.custom.ParameterNotFoundException;
import dgsw.pioneers.checkIn.domain.member.application.domain.exception.DuplicateMemberException;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member.MemberId;
import dgsw.pioneers.checkIn.domain.member.application.port.in.SignUpUseCase;
import dgsw.pioneers.checkIn.domain.member.application.port.out.CreateMemberPort;
import dgsw.pioneers.checkIn.domain.member.application.port.out.ExistMemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.transaction.Transactional;
import java.time.LocalDate;

@UseCase
@Transactional
@RequiredArgsConstructor
public class SignUpService implements SignUpUseCase {

    private final ExistMemberPort existMemberPort;
    private final CreateMemberPort createMemberPort;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUpTeacher(Member member) {

        checkExistMember(member.getMemberId());

        if (!member.checkTeacherRole()) throw new ParameterNotFoundException();
        member.encodePw(passwordEncoder.encode(member.getPw()));

        createMemberPort.createMember(member);
    }

    @Override
    public void signUpStudent(Member member) {

        checkExistMember(member.getMemberId());

        if (!member.checkStudentRole()) throw new ParameterNotFoundException();
        member.encodePw(passwordEncoder.encode(member.getPw()));

        LocalDate now = LocalDate.now();
        member.modifyInfo(now.getYear());

        createMemberPort.createMember(member);
    }

    private void checkExistMember(MemberId signUpCommand) {
        if (existMemberPort.existByMemberId(signUpCommand)) {
            throw new DuplicateMemberException();
        }
    }
}
