package dgsw.pioneers.checkIn.domain.lecture.application.domain.model;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.exception.LectureGradeNotMatchException;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.exception.LecturePeriodNotMatchException;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.exception.LectureStudentExcessException;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.PlaceType;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Lecture {

    private final LectureId lectureId;

    private String explanation;

    private final LectureStatus lectureStatus;

    private final PlaceType placeType;

    private final AcceptableStudent acceptableStudent;

    private final LectureTeacher lectureTeacher;

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
            String explanation,
            LectureStatus lectureStatus,
            PlaceType placeType,
            AcceptableStudent acceptableStudent,
            LectureTeacher lectureTeacher,
            LectureSchedule lectureSchedule,
            int enrollStudent,
            List<WeekPlan> weekPlans,
            List<Participant> participants) {
        return new Lecture(lectureId, explanation, lectureStatus, placeType, acceptableStudent, lectureTeacher, lectureSchedule, enrollStudent, weekPlans, participants);
    }

    public static Lecture teacherWithId(
            String explanation,
            PlaceType placeType,
            AcceptableStudent acceptableStudent,
            LectureTeacher lectureTeacher,
            LectureSchedule lectureSchedule) {
        return new Lecture(null, explanation, LectureStatus.WAITING_PERIOD, placeType, acceptableStudent, lectureTeacher, lectureSchedule, 0,null, null);
    }

    public void updateWeekPlans(List<WeekPlan> weekPlans) {
        this.weekPlans = weekPlans;
    }

    public Participant registerParticipant(Member student) {

        if (!this.lectureStatus.equals(LectureStatus.ENROLMENT)) {
            throw new LecturePeriodNotMatchException();
        }

        if (this.acceptableStudent.getTargetGrade() != student.getStudentInfo().getGrade()) {
            throw new LectureGradeNotMatchException();
        }

        if (this.enrollStudent >= this.acceptableStudent.getMaxStudent()) {
            throw new LectureStudentExcessException();
        }

        this.enrollStudent++;

        Participant participant = Participant.builder()
                .applyDateTime(LocalDateTime.now())
                .memberId(student.getMemberId())
                .build();

        return participant;
    }
}
