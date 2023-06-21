package dgsw.pioneers.checkIn.member.application.port.in;

import dgsw.pioneers.checkIn.common.lib.valid.SelfValidating;
import dgsw.pioneers.checkIn.member.application.domain.model.Member;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Value
@EqualsAndHashCode(callSuper = false)
public class SignInCommand extends SelfValidating<SignInCommand> {

    @NotBlank Member.MemberId memberId;
    @NotBlank String name;
    @NotBlank String pw;
    @Email String email;

    public SignInCommand(
            String memberId,
            String name,
            String pw,
            String email
            ) {
        this.memberId = new Member.MemberId(memberId);
        this.name = name;
        this.pw = pw;
        this.email = email;
        this.validateSelf();
    }
}
