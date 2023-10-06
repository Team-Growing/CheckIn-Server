package dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AttendanceTime {

    LESSON_8_9("8, 9 교시"),
    LESSON_10_11("10, 11 교시");

    private final String lesson;
}
