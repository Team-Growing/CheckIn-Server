package dgsw.pioneers.checkIn.domain.member.application.port.out;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;

public interface ExistMemberPort {

    boolean existByMemberId(Member.MemberId memberId);
}
