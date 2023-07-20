package dgsw.pioneers.checkIn.member.application.port.in;

import dgsw.pioneers.checkIn.member.adapter.in.web.dto.req.SignUpStudentReq;
import dgsw.pioneers.checkIn.member.adapter.in.web.dto.req.SignUpTeacherReq;

public interface SignUpUseCase {

    void signUpTeacher(SignUpTeacherReq signUpTeacherReq);
    void signUpStudent(SignUpStudentReq signUpStudentReq);
}
