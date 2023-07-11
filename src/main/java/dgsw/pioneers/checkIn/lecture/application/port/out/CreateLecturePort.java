package dgsw.pioneers.checkIn.lecture.application.port.out;

import dgsw.pioneers.checkIn.lecture.application.domain.model.Lecture;

public interface CreateLecturePort {

    void createLecture(Lecture lecture);
}
