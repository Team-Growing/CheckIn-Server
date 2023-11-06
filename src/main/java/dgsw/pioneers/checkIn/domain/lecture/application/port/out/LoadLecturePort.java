package dgsw.pioneers.checkIn.domain.lecture.application.port.out;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;

import java.time.DayOfWeek;
import java.util.List;

public interface LoadLecturePort {

    Lecture loadLecture(Lecture.LectureId lectureId);

    Lecture loadLectureWithWeekPlans(Lecture.LectureId lectureId);

    Lecture loadLectureWithParticipants(Lecture.LectureId lectureId);

    List<Lecture> loadAllLectureByStatus(LectureStatus lectureStatus);

    List<Lecture> loadAllLectureByStatusAndTargetGrade(LectureStatus lectureStatus, int targetGrade);

    List<Lecture> loadAllLectureByDayOfWeek(LectureStatus lectureStatus, DayOfWeek dayOfWeek);

    List<Lecture> loadAllLectureByLectureTeacherAndLectureStatus(Member.MemberId memberId, LectureStatus lectureStatus);

    List<Lecture> loadAllLectureByLectureTeacherAndLectureStatusAndDayOfWeek(Member.MemberId teacherId, LectureStatus lectureStatus, DayOfWeek dayOfWeek);
}
