package dgsw.pioneers.checkIn.domain.lecture.application.port.in;

import dgsw.pioneers.checkIn.domain.lecture.adapter.in.web.dto.req.WeekPlanUpdateReq;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole;

public interface WeekPlanUpdateUseCase {
    void updateWeekPlan(Member.MemberId memberId, WeekPlanUpdateReq weekPlanUpdateReq, MemberRole memberRole);
}
