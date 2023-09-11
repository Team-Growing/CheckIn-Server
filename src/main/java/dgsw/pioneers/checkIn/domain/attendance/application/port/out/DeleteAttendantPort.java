package dgsw.pioneers.checkIn.domain.attendance.application.port.out;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendant;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;

public interface DeleteAttendantPort {

   void deleteAttendant(Attendance.AttendanceId attendanceId, Attendant attendant);
}
