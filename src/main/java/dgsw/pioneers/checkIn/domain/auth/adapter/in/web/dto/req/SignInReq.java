package dgsw.pioneers.checkIn.auth.adapter.in.web.dto.req;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class SignInReq {

    @NotBlank
    private String id;
    @NotBlank
    private String pw;
}
