package dgsw.pioneers.checkIn.domain.lecture.application.port.in;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;

public interface EnrolmentUseCase {

    void lectureEnrolment(Lecture.LectureId lectureId, Member.MemberId studentId);
}
