package dgsw.pioneers.checkIn.lecture.adapter.in.web.dto.req;

import dgsw.pioneers.checkIn.lecture.application.domain.model.WeekPlan;
import lombok.Getter;

import java.util.List;

@Getter
public class WeekPlanUpdateReq {

    Long lectureId;
    List<WeekPlan> weekPlans;
}
