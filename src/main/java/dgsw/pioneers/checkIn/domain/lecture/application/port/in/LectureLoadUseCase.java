package dgsw.pioneers.checkIn.domain.lecture.application.port.in;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;

import java.util.List;

public interface LectureLoadUseCase {

    Lecture loadLecture(Lecture.LectureId lectureId);

    List<Lecture> loadAllCoursePeriodLecture();

    List<Lecture> loadAllLectureByStatusAndTargetGrade(LectureStatus lectureStatus, int targetGrade);

    List<Lecture> loadTodayLecture();
}
