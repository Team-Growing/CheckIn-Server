package dgsw.pioneers.checkIn.lecture.application.port.out;

import dgsw.pioneers.checkIn.lecture.application.domain.model.Lecture;

public interface PersistenceLecturePort {

    void persistenceLecture(Lecture lecture);
}
