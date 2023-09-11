package dgsw.pioneers.checkIn.domain.lecture.application.domain.service;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.port.in.WeekPlanUpdateUseCase;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.UpdateLectureWeekPlansPort;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import dgsw.pioneers.checkIn.global.exception.custom.PermissionInvalidException;
import dgsw.pioneers.checkIn.domain.lecture.adapter.in.web.dto.req.WeekPlanUpdateReq;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.LoadLecturePort;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WeekPlanUpdateService implements WeekPlanUpdateUseCase {

    private final LoadLecturePort loadLecturePort;
    private final UpdateLectureWeekPlansPort updateLectureWeekPlansPort;

    @Override
    @Transactional
    public void updateWeekPlan(Member.MemberId memberId, WeekPlanUpdateReq weekPlanUpdateReq, MemberRole memberRole) {

        Lecture lecture = loadLecturePort.loadLectureWithWeekPlans(new Lecture.LectureId(weekPlanUpdateReq.getLectureId()));

        if (memberRole.equals(MemberRole.TEACHER)) {
            if (!lecture.getLectureTeacher().getMemberId().equals(memberId)) throw new PermissionInvalidException();
        }

        lecture.updateWeekPlans(weekPlanUpdateReq.getWeekPlans());
        updateLectureWeekPlansPort.updateLectureWeekPlans(lecture);
    }
}
