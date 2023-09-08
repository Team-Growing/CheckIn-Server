package dgsw.pioneers.checkIn.domain.member.application.port.in;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;

import java.util.List;

public interface MemberLoadUseCase {

    Member loadMember(Member.MemberId memberId);
    List<Member> loadTeachers();
}
