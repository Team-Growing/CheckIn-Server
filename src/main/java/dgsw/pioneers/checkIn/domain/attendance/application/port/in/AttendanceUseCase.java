package dgsw.pioneers.checkIn.domain.attendance.application.port.in;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;

public interface AttendanceUseCase {

    void attendance(Lecture.LectureId lectureId, Member.MemberId memberId);

    void attendanceByCode(Lecture.LectureId lectureId, Member.MemberId memberId, String code);
}
