package dgsw.pioneers.checkIn.domain.lecture.application.port.out;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;

public interface CreateLecturePort {

    void createLecture(Lecture lecture);
}
