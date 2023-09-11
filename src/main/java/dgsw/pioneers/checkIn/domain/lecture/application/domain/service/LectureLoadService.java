package dgsw.pioneers.checkIn.domain.lecture.application.domain.service;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.domain.lecture.application.port.in.LectureLoadUseCase;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.LoadLecturePort;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.port.out.LoadMemberPort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import dgsw.pioneers.checkIn.global.lib.zonedatetime.ZoneDateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LectureLoadService implements LectureLoadUseCase {

    private final LoadLecturePort loadLecturePort;
    private final LoadMemberPort loadMemberPort;

    @Override
    public Lecture loadLecture(Lecture.LectureId lectureId) {

        Lecture lecture = loadLecturePort.loadLectureWithWeekPlans(lectureId);
        updateTeacherInfo(lecture);

        return lecture;
    }

    @Override
    public List<Lecture> loadAllCoursePeriodLecture() {
        return loadLecturePort.loadAllLectureByStatus(LectureStatus.COURSE_PERIOD);
    }

    @Override
    public List<Lecture> loadTodayLecture() {

        DayOfWeek dayOfWeek = ZoneDateTimeUtil.nowToLocalDate().getDayOfWeek();

        List<Lecture> lectures = loadLecturePort.loadAllLectureByDayOfWeek(LectureStatus.COURSE_PERIOD, dayOfWeek);
        lectures.forEach(this::updateTeacherInfo);

        return lectures;
    }

    private void updateTeacherInfo(Lecture lecture) {

        Member teacher = loadMemberPort.loadMember(lecture.getLectureTeacher().getMemberId());
        lecture.updateTeacherInfo(teacher.getName());
    }
}
