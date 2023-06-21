package dgsw.pioneers.checkIn.member.adapter.in.web.request;

import dgsw.pioneers.checkIn.member.application.domain.model.MemberRole;
import lombok.Getter;

@Getter
public class SignUpRequest {

    private String id;
    private String email;
    private String pw;
    private String name;
    private MemberRole memberRole;
}
