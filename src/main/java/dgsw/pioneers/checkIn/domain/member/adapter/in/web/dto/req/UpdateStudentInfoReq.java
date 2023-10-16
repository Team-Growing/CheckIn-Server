package dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.req;

import dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.StudentInfoDto;
import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class UpdateStudentInfoReq {

    @Email
    private String email;
    private String pw;
    private StudentInfoDto studentInfo;
}
