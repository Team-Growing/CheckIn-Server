package dgsw.pioneers.checkIn.domain.lecture.application.port.in;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;

public interface LectureGenerateUseCase {
    void generateLecture(Lecture lecture);
}
