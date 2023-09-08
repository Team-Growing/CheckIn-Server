package dgsw.pioneers.checkIn.domain.member.application.port.out;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole;

import java.util.List;

public interface LoadMemberPort {

    Member loadMember(Member.MemberId memberId);
    List<Member> loadTeachers(MemberRole memberRole);
}
