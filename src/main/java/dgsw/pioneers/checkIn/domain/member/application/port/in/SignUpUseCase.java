package dgsw.pioneers.checkIn.domain.member.application.port.in;

import dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.req.SignUpStudentReq;
import dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.req.SignUpTeacherReq;

public interface SignUpUseCase {

    void signUpTeacher(SignUpTeacherReq signUpTeacherReq);
    void signUpStudent(SignUpStudentReq signUpStudentReq);
}
