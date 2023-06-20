package dgsw.pioneers.checkIn.member.adapter.in.web.request;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class SignInRequest {

    @Email
    private String email;
    @NotBlank
    private String pw;
    @NotBlank
    private String name;
}
