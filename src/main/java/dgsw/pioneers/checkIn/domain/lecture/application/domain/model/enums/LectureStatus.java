package dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LectureStatus {

    WAITING_PERIOD("대기"),
    ENROLMENT("수강신청"),
    COURSE_PERIOD("수강중"),
    TERMINATION("종료");

    private final String explanation;
}
