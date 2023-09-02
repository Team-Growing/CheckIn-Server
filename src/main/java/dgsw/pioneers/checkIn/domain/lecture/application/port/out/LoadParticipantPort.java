package dgsw.pioneers.checkIn.domain.lecture.application.port.out;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;

public interface LoadParticipantPort {

    boolean existByLectureIdAndMemberId(Lecture.LectureId lectureId, Member.MemberId memberId);
}
