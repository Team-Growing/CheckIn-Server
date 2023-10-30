package dgsw.pioneers.checkIn.domain.attendance.application.port.in;

import dgsw.pioneers.checkIn.domain.absence.application.domain.model.Absence;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;

import java.util.List;

public interface AttendantsLoadUseCase {

    List<Member> loadAttendants(Lecture.LectureId lectureId);
    List<Member> loadNonAttendants(Lecture.LectureId lectureId, List<Member> members, List<Absence> absences);
}
