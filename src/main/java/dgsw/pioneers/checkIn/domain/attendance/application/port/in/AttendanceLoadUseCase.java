package dgsw.pioneers.checkIn.domain.attendance.application.port.in;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;

public interface AttendanceLoadUseCase {

    String getAttendanceCode(Lecture.LectureId lectureId);
}
