package dgsw.pioneers.checkIn.domain.lecture.application.port.in;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.WeekPlan;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;

import java.util.List;

public interface WeekPlanUpdateUseCase {
    void updateWeekPlan(Member member, Lecture.LectureId lectureId, List<WeekPlan> weekPlans);
}
