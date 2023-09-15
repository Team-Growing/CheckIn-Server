package dgsw.pioneers.checkIn.domain.member.application.port.out;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendant;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole;

import java.util.List;

public interface LoadMemberPort {

    Member loadMember(Member.MemberId memberId);
    List<Member> loadTeachers(MemberRole memberRole);
    List<Member> loadAttendants(Lecture.LectureId lectureId, AttendanceStatus attendanceStatus);

    //정보 : 제네릭 타입은 메서드 시그니처가 같다, 제네릭은 컴파일 시 타입 체크, 컴파일러는 메서드 시그니처만 고려함
    //그래서 아리의 두 메서드는 동일한 메서드로 간주
    List<Member> loadNonAttendantsByMember(Lecture.LectureId lectureId, List<Member> members);
    List<Member.MemberId> loadNonAttendantsByAttendant(Lecture.LectureId lectureId, List<Attendant> attendants);
}
