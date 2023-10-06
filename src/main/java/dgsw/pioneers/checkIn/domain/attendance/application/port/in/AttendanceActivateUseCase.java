package dgsw.pioneers.checkIn.domain.attendance.application.port.in;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceTime;

public interface AttendanceActivateUseCase {

    void activate(AttendanceTime attendanceTime);
    void deactivate(AttendanceTime attendanceTime);
}
