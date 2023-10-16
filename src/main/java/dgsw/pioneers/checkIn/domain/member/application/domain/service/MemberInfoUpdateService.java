package dgsw.pioneers.checkIn.domain.member.application.domain.service;

import dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.req.UpdateStudentInfoReq;
import dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.req.UpdateTeacherInfoReq;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.port.in.MemberInfoUpdateUseCase;
import dgsw.pioneers.checkIn.domain.member.application.port.out.UpdateMemberPort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import dgsw.pioneers.checkIn.global.lib.zonedatetime.ZoneDateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberInfoUpdateService implements MemberInfoUpdateUseCase {

    private final PasswordEncoder passwordEncoder;
    private final UpdateMemberPort updateMemberPort;

    @Override
    @Transactional
    public void updateTeacherInfo(Member member, UpdateTeacherInfoReq updateTeacherInfoReq) {

        Optional.ofNullable(updateTeacherInfoReq.getEmail())
                .ifPresent(member::updateEmail);

        Optional.ofNullable(updateTeacherInfoReq.getPw())
                .ifPresent(pw -> member.encodePw(passwordEncoder.encode(pw)));

        Optional.ofNullable(updateTeacherInfoReq.getSubject())
                .ifPresent(member::updateSubject);

        updateMemberPort.updateTeacher(member);
    }

    @Override
    @Transactional
    public void updateStudentInfo(Member member, UpdateStudentInfoReq updateStudentInfoReq) {

        Optional.ofNullable(updateStudentInfoReq.getEmail())
                .ifPresent(member::updateEmail);

        Optional.ofNullable(updateStudentInfoReq.getPw())
                .ifPresent(pw -> member.encodePw(passwordEncoder.encode(pw)));

        Optional.ofNullable(updateStudentInfoReq.getStudentInfo())
                .ifPresent(studentInfo -> member.updateStudentInfo(
                        ZoneDateTimeUtil.nowToLocalDate().getYear(),
                        studentInfo.getGrade(),
                        studentInfo.getRoom(),
                        studentInfo.getNumber())
                );

        updateMemberPort.updateStudent(member);
    }
}
