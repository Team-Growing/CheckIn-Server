package dgsw.pioneers.checkIn.lecture.application.port.in;

import dgsw.pioneers.checkIn.lecture.adapter.in.web.dto.req.WeekPlanUpdateReq;
import dgsw.pioneers.checkIn.member.application.domain.model.Member;

public interface WeekPlanUpdateUseCase {
    void updateWeekPlan(Member.MemberId memberId, WeekPlanUpdateReq weekPlanUpdateReq);
}
