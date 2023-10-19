package dgsw.pioneers.checkIn.domain.absence.application.domain.service;

import dgsw.pioneers.checkIn.domain.absence.adapter.in.web.dto.req.AbsenceGenerateReq;
import dgsw.pioneers.checkIn.domain.absence.application.domain.exception.AbsenceDuplicatedException;
import dgsw.pioneers.checkIn.domain.absence.application.domain.exception.AbsenceNotMatchException;
import dgsw.pioneers.checkIn.domain.absence.application.domain.model.Absence;
import dgsw.pioneers.checkIn.domain.absence.application.port.in.AbsenceUseCase;
import dgsw.pioneers.checkIn.domain.absence.application.port.out.CreateAbsencePort;
import dgsw.pioneers.checkIn.domain.absence.application.port.out.LoadAbsencePort;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance.AttendanceId;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import dgsw.pioneers.checkIn.domain.attendance.application.port.out.LoadAttendancePort;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture.LectureId;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.LoadParticipantPort;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member.MemberId;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AbsenceService implements AbsenceUseCase {

    private final LoadAbsencePort loadAbsencePort;
    private final CreateAbsencePort createAbsencePort;
    private final LoadAttendancePort loadAttendancePort;
    private final LoadParticipantPort loadParticipantPort;

    @Override
    @Transactional
    public void absence(AbsenceGenerateReq req, MemberId memberId) {

        LectureId lectureId = new LectureId(req.getLectureId());

        validateParticipant(lectureId, memberId);

        AttendanceId attendanceId = loadAttendancePort.loadAttendanceByLectureAndAttendanceStatus(
                lectureId,
                AttendanceStatus.PERIOD_VALID).getAttendanceId();

        validateNotDuplicate(attendanceId, memberId);

        Absence absence = Absence.builder()
                .reason(req.getReason())
                .lectureId(lectureId)
                .attendanceId(attendanceId)
                .memberId(memberId).build();

        createAbsencePort.createAbsence(absence);
    }

    private void validateParticipant(LectureId lectureId, MemberId memberId) {
        if (!loadParticipantPort.existByLectureIdAndMemberId(lectureId, memberId)) {
            throw new AbsenceNotMatchException();
        }
    }

    private void validateNotDuplicate(AttendanceId attendanceId, MemberId memberId) {
        if (loadAbsencePort.existByAttendanceIdAndMemberId(attendanceId, memberId)) {
            throw new AbsenceDuplicatedException();
        }
    }
}
