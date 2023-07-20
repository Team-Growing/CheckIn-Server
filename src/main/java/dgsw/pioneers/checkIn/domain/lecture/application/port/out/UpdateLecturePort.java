package dgsw.pioneers.checkIn.domain.lecture.application.port.out;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;

public interface UpdateLecturePort {

    void updateLecture(Lecture lecture);
}
