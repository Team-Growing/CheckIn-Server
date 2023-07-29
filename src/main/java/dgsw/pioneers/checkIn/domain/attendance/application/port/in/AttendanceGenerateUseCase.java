package dgsw.pioneers.checkIn.domain.attendance.application.port.in;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;

public interface AttendanceGenerateUseCase {

    void generateAttendance(Attendance attendance);
}
