package dgsw.pioneers.checkIn.domain.member.application.port.in;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;

public interface MemberLoadUseCase {

    Member loadMember(Member.MemberId memberId);
}
