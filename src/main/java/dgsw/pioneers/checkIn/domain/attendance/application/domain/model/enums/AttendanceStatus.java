package dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AttendanceStatus {

    PERIOD_VALID("출석 기간"),
    PERIOD_EXPIRED_BEFORE("출석 기간 전"),
    PERIOD_EXPIRED_AFTER("출석 기간 후");

    private final String explanation;
}
