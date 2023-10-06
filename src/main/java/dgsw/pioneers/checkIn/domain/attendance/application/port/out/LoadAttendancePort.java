package dgsw.pioneers.checkIn.domain.attendance.application.port.out;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceTime;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;

import java.time.LocalDate;
import java.util.List;

public interface LoadAttendancePort {

    Attendance loadAttendanceByLectureAndAttendanceStatus(Lecture.LectureId lectureId, AttendanceStatus attendanceStatus);
    Attendance loadAttendanceByLectureAndAttendanceStatusWithAttendants(Lecture.LectureId lectureId, AttendanceStatus attendanceStatus);
    List<Attendance> loadAttendanceByLectureDateAndAttendanceTime(LocalDate lectureDate, AttendanceTime attendanceTime);
}
