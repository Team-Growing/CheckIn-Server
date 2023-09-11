package dgsw.pioneers.checkIn.domain.lecture.application.port.out;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;

import java.time.DayOfWeek;
import java.util.List;

public interface LoadLecturePort {

    Lecture loadLecture(Lecture.LectureId lectureId);

    List<Lecture> loadAllLectureByStatus(LectureStatus lectureStatus);

    List<Lecture> loadAllLectureByDayOfWeek(LectureStatus lectureStatus, DayOfWeek dayOfWeek);
}
