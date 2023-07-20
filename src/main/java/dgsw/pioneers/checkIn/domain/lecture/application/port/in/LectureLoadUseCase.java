package dgsw.pioneers.checkIn.domain.lecture.application.port.in;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;

import java.util.List;

public interface LectureLoadUseCase {

    Lecture loadLecture(Lecture.LectureId lectureId);

    List<Lecture> loadAllLecture();
}
