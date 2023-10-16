package dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.req;

import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class UpdateTeacherInfoReq {

    @Email
    private String email;
    private String pw;
    private String subject;
}
