package dgsw.pioneers.checkIn.domain.member.application.port.out;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole;

import java.util.List;

public interface LoadMemberPort {

    Member loadMember(Member.MemberId memberId);
    List<Member> loadTeachers(MemberRole memberRole);
    List<Member> loadAttendants(Lecture.LectureId lectureId, AttendanceStatus attendanceStatus);
    List<Member> loadNonAttendants(Lecture.LectureId lectureId, List<Member> members);
}
