package dgsw.pioneers.checkIn.domain.lecture.application.port.in;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture.LectureId;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;

import java.util.List;

public interface LectureStatusUpdateUseCase {

    void updateStatus(List<LectureId> lectureIds, LectureStatus lectureStatus);
}
