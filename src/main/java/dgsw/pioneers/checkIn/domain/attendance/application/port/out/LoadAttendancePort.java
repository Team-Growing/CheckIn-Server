package dgsw.pioneers.checkIn.domain.attendance.application.port.out;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;

public interface LoadAttendancePort {

    Attendance loadAttendanceByLectureAndAttendanceStatus(Lecture.LectureId lectureId, AttendanceStatus attendanceStatus);
    Attendance loadAttendanceByLectureAndAttendanceStatusWithAttendants(Lecture.LectureId lectureId, AttendanceStatus attendanceStatus);
}
