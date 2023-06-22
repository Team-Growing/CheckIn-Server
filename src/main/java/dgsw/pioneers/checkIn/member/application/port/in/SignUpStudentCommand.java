package dgsw.pioneers.checkIn.member.application.port.in;

import dgsw.pioneers.checkIn.global.lib.valid.SelfValidating;
import dgsw.pioneers.checkIn.member.adapter.in.web.dto.StudentInfo;
import dgsw.pioneers.checkIn.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.member.application.domain.model.Member.MemberId;
import dgsw.pioneers.checkIn.member.application.domain.model.MemberRole;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class SignUpStudentCommand extends SelfValidating<SignUpStudentCommand> {

    @NotNull MemberId memberId;
    @NotBlank String name;
    @NotBlank String pw;
    @Email String email;
    @NotNull MemberRole memberRole;
    @NotNull dgsw.pioneers.checkIn.member.application.domain.model.StudentInfo studentInfo;

    public SignUpStudentCommand(
            String memberId,
            String name,
            String pw,
            String email,
            MemberRole memberRole,
            StudentInfo studentInfo) {
        this.memberId = new MemberId(memberId);
        this.name = name;
        this.pw = pw;
        this.email = email;
        this.memberRole = memberRole;
        this.studentInfo = dgsw.pioneers.checkIn.member.application.domain.model.StudentInfo.builder()
                .grade(studentInfo.getGrade())
                .room(studentInfo.getRoom())
                .number(studentInfo.getNumber()).build();
        this.validateSelf();
    }

    public Member mapToDomainEntity() {
        return Member.withId(
                this.memberId,
                this.name,
                this.pw,
                this.email,
                this.memberRole,
                this.studentInfo
        );
    }
}
