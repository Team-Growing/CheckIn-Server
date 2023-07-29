package dgsw.pioneers.checkIn.domain.member.application.port.in;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;

public interface SignUpUseCase {

    void signUpTeacher(Member member);
    void signUpStudent(Member member);
}
