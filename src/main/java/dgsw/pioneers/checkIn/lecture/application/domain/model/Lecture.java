package dgsw.pioneers.checkIn.lecture.application.domain.model;

import dgsw.pioneers.checkIn.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.lecture.application.domain.model.enums.PlaceType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

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

    private List<WeekPlan> weekPlans;

    @Value
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
            List<WeekPlan> weekPlans) {
        return new Lecture(lectureId, explanation, lectureStatus, placeType, acceptableStudent, lectureTeacher, lectureSchedule, weekPlans);
    }

    public static Lecture teacherWithId(
            String explanation,
            PlaceType placeType,
            AcceptableStudent acceptableStudent,
            LectureTeacher lectureTeacher,
            LectureSchedule lectureSchedule) {
        return new Lecture(null, explanation, LectureStatus.WAITING_PERIOD, placeType, acceptableStudent, lectureTeacher, lectureSchedule, null);
    }

    public void updateWeekPlans(List<WeekPlan> weekPlans) {
        this.weekPlans = weekPlans;
    }
}
