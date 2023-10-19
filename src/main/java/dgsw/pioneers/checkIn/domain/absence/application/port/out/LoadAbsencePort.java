package dgsw.pioneers.checkIn.domain.absence.application.port.out;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance.AttendanceId;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member.MemberId;
import dgsw.pioneers.checkIn.domain.absence.application.domain.model.Absence;

import java.util.List;

public interface LoadAbsencePort {

    Absence loadAbsenceById(Absence.AbsenceId absenceId);
    Absence loadAbsenceByIdWithMember(Absence.AbsenceId absenceId);
    List<Absence> loadAbsence();
    boolean existByAttendanceIdAndMemberId(AttendanceId attendanceId, MemberId memberId);
}
