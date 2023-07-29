package dgsw.pioneers.checkIn.domain.lecture.application.port.out;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;

import java.util.List;

public interface LoadLecturePort {

    Lecture loadLecture(Long lectureId);

    List<Lecture> loadAllLectureByStatus(LectureStatus lectureStatus);
}
