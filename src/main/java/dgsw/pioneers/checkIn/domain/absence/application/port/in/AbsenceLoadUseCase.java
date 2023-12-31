package dgsw.pioneers.checkIn.domain.absence.application.port.in;

import dgsw.pioneers.checkIn.domain.absence.application.domain.model.Absence;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;

import java.time.LocalDate;
import java.util.List;

public interface AbsenceLoadUseCase {

    List<Absence> loadAbsenceForAttendance(Lecture.LectureId lectureId);

    List<Absence> loadMyAbsence(Member.MemberId memberId);

    List<Absence> loadAbsence(LocalDate date);
}
