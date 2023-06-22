package dgsw.pioneers.checkIn.member.adapter.in.web.dto.req;

import dgsw.pioneers.checkIn.member.adapter.in.web.dto.StudentInfo;
import dgsw.pioneers.checkIn.member.application.domain.model.MemberRole;
import lombok.Getter;

@Getter
public class SignUpStudentReq {

    private String id;
    private String email;
    private String pw;
    private String name;
    private MemberRole memberRole;
    private StudentInfo studentInfo;
}
