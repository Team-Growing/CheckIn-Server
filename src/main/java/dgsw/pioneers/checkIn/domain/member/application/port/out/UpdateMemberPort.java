package dgsw.pioneers.checkIn.domain.member.application.port.out;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;

public interface UpdateMemberPort {

    void updateStudent(Member member);
    void updateTeacher(Member member);
}
