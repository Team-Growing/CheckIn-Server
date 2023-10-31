package dgsw.pioneers.checkIn.domain.absence.application.port.out;

import dgsw.pioneers.checkIn.domain.absence.application.domain.model.enums.AbsenceStatus;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance.AttendanceId;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member.MemberId;
import dgsw.pioneers.checkIn.domain.absence.application.domain.model.Absence;

import java.time.LocalDate;
import java.util.List;

public interface LoadAbsencePort {

    Absence loadAbsenceById(Absence.AbsenceId absenceId);
    List<Absence> loadAbsences(LocalDate date);
    List<Absence> loadAbsencesByMemberIdAndCreatedAt(MemberId memberId, LocalDate now);
    boolean existByAttendanceIdAndMemberId(AttendanceId attendanceId, MemberId memberId);
    List<Absence> loadAbsencesByLectureIdAndCreatedAtAndAbsenceStatus(Lecture.LectureId lectureId, AbsenceStatus absenceAllowed, LocalDate now);
}
