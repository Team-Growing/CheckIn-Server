package dgsw.pioneers.checkIn.member.adapter.in.web.request;

import lombok.Getter;

@Getter
public class SignInRequest {

    private String id;
    private String email;
    private String pw;
    private String name;
}
