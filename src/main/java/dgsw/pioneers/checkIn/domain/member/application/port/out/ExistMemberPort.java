package dgsw.pioneers.checkIn.member.application.port.out;

import dgsw.pioneers.checkIn.member.application.domain.model.Member.MemberId;

public interface ExistMemberPort {

    boolean existByMemberId(MemberId memberId);
}
