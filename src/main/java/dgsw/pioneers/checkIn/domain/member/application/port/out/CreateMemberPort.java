package dgsw.pioneers.checkIn.member.application.port.out;

import dgsw.pioneers.checkIn.member.application.domain.model.Member;

public interface CreateMemberPort {

    void createMember(Member member);
}
