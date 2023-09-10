package dgsw.pioneers.checkIn.domain.attendance.application.port.in;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;

public interface AttendanceCodeReissueUseCase {

    void reissueCode(Lecture.LectureId lectureId);
}
