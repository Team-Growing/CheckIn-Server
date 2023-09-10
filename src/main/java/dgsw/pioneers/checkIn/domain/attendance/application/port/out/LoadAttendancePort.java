package dgsw.pioneers.checkIn.domain.attendance.application.port.out;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;

public interface LoadAttendancePort {

    Attendance loadAttendanceByLectureAndAttendanceStatus(Long lectureId, AttendanceStatus attendanceStatus);
    Attendance loadAttendanceByLectureAndAttendanceStatusWithAttendants(Long lectureId, AttendanceStatus attendanceStatus);
}
