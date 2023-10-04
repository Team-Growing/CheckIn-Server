package dgsw.pioneers.checkIn.domain.lecture.application.domain.model;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.exception.LectureDayOfWeekNotAllowException;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.exception.LectureGradeNotMatchException;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.exception.LecturePeriodNotMatchException;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.exception.LectureStudentExcessException;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureTag;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.PlaceType;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.global.lib.zonedatetime.ZoneDateTimeUtil;
import lombok.*;

import java.time.DayOfWeek;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Lecture {

    private final LectureId lectureId;
    private final String lectureName;

    private String explanation;

    private LectureStatus lectureStatus;

    private final PlaceType placeType;

    private final LectureTag lectureTag;

    private final AcceptableStudent acceptableStudent;

    private LectureTeacher lectureTeacher;

    private final LectureSchedule lectureSchedule;

    private int enrollStudent;

    private List<WeekPlan> weekPlans;

    private List<Participant> participants;

    @Value
    @AllArgsConstructor
    public static class LectureId {
        Long value;
    }

    public static Lecture withId(
            LectureId lectureId,
            String lectureName,
            String explanation,
            LectureStatus lectureStatus,
            PlaceType placeType,
            LectureTag lectureTag,
            AcceptableStudent acceptableStudent,
            LectureTeacher lectureTeacher,
            LectureSchedule lectureSchedule,
            int enrollStudent,
            List<WeekPlan> weekPlans,
            List<Participant> participants) {
        return new Lecture(lectureId, lectureName, explanation, lectureStatus, placeType, lectureTag, acceptableStudent, lectureTeacher, lectureSchedule, enrollStudent, weekPlans, participants);
    }

    public static Lecture teacherWithId(
            String lectureName,
            String explanation,
            PlaceType placeType,
            LectureTag lectureTag,
            AcceptableStudent acceptableStudent,
            LectureTeacher lectureTeacher,
            LectureSchedule lectureSchedule) {

        for (DayOfWeek dayOfWeek : lectureSchedule.getDayOfWeek()) {
            if (dayOfWeek.equals(DayOfWeek.FRIDAY)) {
                throw new LectureDayOfWeekNotAllowException();
            }

            if (dayOfWeek.equals(DayOfWeek.SATURDAY)) {
                throw new LectureDayOfWeekNotAllowException();
            }

            if (dayOfWeek.equals(DayOfWeek.SUNDAY)) {
                throw new LectureDayOfWeekNotAllowException();
            }
        }

        return new Lecture(null, lectureName, explanation, LectureStatus.WAITING_PERIOD, placeType, lectureTag, acceptableStudent, lectureTeacher, lectureSchedule, 0,null, null);
    }

    public void updateWeekPlans(List<WeekPlan> weekPlans) {
        this.weekPlans = weekPlans;
    }

    public void updateLectureStatus(LectureStatus lectureStatus) {
        this.lectureStatus = lectureStatus;
    }

    public void addParticipant(Member student) {

        if (!this.lectureStatus.equals(LectureStatus.ENROLMENT)) {
            throw new LecturePeriodNotMatchException();
        }

        if (this.acceptableStudent.getTargetGrade() != student.getStudentInfo().getGrade()) {
            throw new LectureGradeNotMatchException();
        }

        if (this.enrollStudent >= this.acceptableStudent.getMaxStudent()) {
            throw new LectureStudentExcessException();
        }

        this.participants.add(Participant.builder()
                .applyDateTime(ZoneDateTimeUtil.nowToLocalDateTime())
                .participantId(student.getMemberId())
                .build());
        this.enrollStudent++;
    }

    public void updateTeacherInfo(String teacherName) {
        this.lectureTeacher = LectureTeacher.builder()
                .teacherId(this.lectureTeacher.getTeacherId())
                .name(teacherName)
                .build();
    }
}
