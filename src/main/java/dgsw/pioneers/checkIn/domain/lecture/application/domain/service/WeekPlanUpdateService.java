package dgsw.pioneers.checkIn.lecture.application.domain.service;

import dgsw.pioneers.checkIn.global.annotation.UseCase;
import dgsw.pioneers.checkIn.global.exception.custom.PermissionInvalidException;
import dgsw.pioneers.checkIn.lecture.adapter.in.web.dto.req.WeekPlanUpdateReq;
import dgsw.pioneers.checkIn.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.lecture.application.port.in.WeekPlanUpdateUseCase;
import dgsw.pioneers.checkIn.lecture.application.port.out.LoadLecturePort;
import dgsw.pioneers.checkIn.lecture.application.port.out.PersistenceLecturePort;
import dgsw.pioneers.checkIn.member.application.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WeekPlanUpdateService implements WeekPlanUpdateUseCase {

    private final LoadLecturePort loadLecturePort;
    private final PersistenceLecturePort persistenceLecturePort;

    @Override
    @Transactional
    public void updateWeekPlan(Member.MemberId memberId, WeekPlanUpdateReq weekPlanUpdateReq) {

        Lecture lecture = loadLecturePort.loadLecture(weekPlanUpdateReq.getLectureId());
        if (lecture.getLectureTeacher().getMemberId().equals(memberId)) throw new PermissionInvalidException();

        lecture.updateWeekPlans(weekPlanUpdateReq.getWeekPlans());
        persistenceLecturePort.persistenceLecture(lecture);
    }
}
