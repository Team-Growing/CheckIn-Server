package dgsw.pioneers.checkIn.domain.lecture.application.port.in;

import dgsw.pioneers.checkIn.domain.lecture.adapter.in.web.dto.res.LectureAllListRes;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole;

import java.util.List;

public interface LectureLoadUseCase {

    Lecture loadLecture(Lecture.LectureId lectureId);

    LectureAllListRes loadAllLecture(int targetGrade);

    List<Lecture> loadAllCoursePeriodLecture();

    List<Lecture> loadAllLectureByStatusAndTargetGrade(LectureStatus lectureStatus, MemberRole memberRole, int targetGrade);

    List<Lecture> loadTodayLecture();
}
