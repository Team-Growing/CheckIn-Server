package dgsw.pioneers.checkIn.domain.attendance.model.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AttendanceStatus {

    PERIOD_VALID("출석 유효"),
    PERIOD_EXPIRED("출석 만료");

    private final String explanation;
}
