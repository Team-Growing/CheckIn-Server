package dgsw.pioneers.checkIn.domain.lecture.application.domain.service;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.WeekPlan;
import dgsw.pioneers.checkIn.domain.lecture.application.port.in.WeekPlanUpdateUseCase;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.UpdateLectureWeekPlansPort;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import dgsw.pioneers.checkIn.global.exception.custom.PermissionInvalidException;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.LoadLecturePort;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WeekPlanUpdateService implements WeekPlanUpdateUseCase {

    private final LoadLecturePort loadLecturePort;
    private final UpdateLectureWeekPlansPort updateLectureWeekPlansPort;

    @Override
    @Transactional
    public void updateWeekPlan(Member member, Lecture.LectureId lectureId, List<WeekPlan> weekPlans) {

        Lecture lecture = loadLecturePort.loadLectureWithWeekPlans(lectureId);

        if (member.getMemberRole().equals(MemberRole.TEACHER)) {
            if (!lecture.getLectureTeacher().getTeacherId().equals(member.getMemberId())) throw new PermissionInvalidException();
        }

        lecture.updateWeekPlans(weekPlans);
        updateLectureWeekPlansPort.updateLectureWeekPlans(lecture);
    }
}
