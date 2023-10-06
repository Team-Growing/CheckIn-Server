package dgsw.pioneers.checkIn.domain.attendance.application.port.out;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;

public interface UpdateAttendanceStatusPort {

    void updateAttendanceStatus(Attendance attendance);
}
