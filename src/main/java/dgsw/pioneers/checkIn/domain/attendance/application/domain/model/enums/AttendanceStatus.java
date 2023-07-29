package dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AttendanceStatus {

    PERIOD_VALID("출석 기간"),
    PERIOD_EXPIRED("출석 기간이 아님");

    private final String explanation;
}
