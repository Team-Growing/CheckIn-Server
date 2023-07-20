package dgsw.pioneers.checkIn.domain.member.application.port.out;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;

public interface LoadMemberPort {

    Member loadMember(Member.MemberId memberId);
}
