package dgsw.pioneers.checkIn.member.application.port.in;

import dgsw.pioneers.checkIn.global.lib.valid.SelfValidating;
import dgsw.pioneers.checkIn.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.member.application.domain.model.Member.MemberId;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class SignUpTeacherCommand extends SelfValidating<SignUpTeacherCommand> {

    @NotNull MemberId memberId;
    @NotBlank String name;
    @NotBlank String pw;
    @Email String email;

    public SignUpTeacherCommand(
            String memberId,
            String name,
            String pw,
            String email
            ) {
        this.memberId = new MemberId(memberId);
        this.name = name;
        this.pw = pw;
        this.email = email;
        this.validateSelf();
    }

    public Member mapToDomainEntity() {
        return Member.teacherWithId(
                this.memberId,
                this.name,
                this.pw,
                this.email,
                null
        );
    }
}
