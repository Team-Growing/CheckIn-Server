package dgsw.pioneers.checkIn.domain.lecture.application.port.in;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;

public interface LectureStatusUpdateUseCase {

    void updateStatus(Lecture.LectureId lectureId, LectureStatus lectureStatus);
}
