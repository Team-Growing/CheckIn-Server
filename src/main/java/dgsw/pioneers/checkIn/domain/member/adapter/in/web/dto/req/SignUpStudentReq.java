package dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.req;

import dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.StudentInfoDto;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.StudentInfo;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class SignUpStudentReq {

    @NotBlank
    private String id;
    @Email
    private String email;
    @NotBlank
    private String pw;
    @NotBlank
    private String name;
    @NotNull
    private StudentInfoDto studentInfo;

    public Member mapToDomainEntity() {
        return Member.studentWithId(
                new Member.MemberId(id),
                this.name,
                this.pw,
                this.email,
                StudentInfo.builder()
                        .grade(studentInfo.getGrade())
                        .room(studentInfo.getRoom())
                        .number(studentInfo.getNumber()).build()
        );
    }
}
