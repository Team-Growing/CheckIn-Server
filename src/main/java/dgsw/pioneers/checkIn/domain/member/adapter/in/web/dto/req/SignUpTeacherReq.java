package dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.req;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class SignUpTeacherReq {

    @NotBlank
    private String id;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String pw;
    @NotBlank
    private String name;
    @NotBlank
    private String subject;

    public Member mapToDomainEntity() {
        return Member.teacherWithId(
                new Member.MemberId(id),
                this.name,
                this.pw,
                this.email,
                null,
                this.subject
        );
    }
}
