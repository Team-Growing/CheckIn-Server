package dgsw.pioneers.checkIn.domain.lecture.application.port.out;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;

import java.util.List;

public interface LoadLectureByParticipantPort {

    List<Lecture> loadAllLectureByMemberAndStatus(Member.MemberId memberId, LectureStatus lectureStatus);
}
