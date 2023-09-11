package dgsw.pioneers.checkIn.domain.attendance.application.port.in;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;

public interface AttendanceEradicateUseCase {

    void eradicate(Lecture.LectureId lectureId, Member.MemberId memberId);
}
