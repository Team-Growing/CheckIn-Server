package dgsw.pioneers.checkIn.auth.application.port.in;

import dgsw.pioneers.checkIn.member.application.domain.model.Member.MemberId;
import dgsw.pioneers.checkIn.global.lib.valid.SelfValidating;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class SignInCommand extends SelfValidating<SignInCommand> {

    @NotNull MemberId memberId;
    @NotBlank String pw;

    public SignInCommand(
            String memberId,
            String pw
    ) {
        this.memberId = new MemberId(memberId);
        this.pw = pw;
    }
}
