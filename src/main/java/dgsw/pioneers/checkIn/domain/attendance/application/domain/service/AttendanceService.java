package dgsw.pioneers.checkIn.domain.attendance.application.domain.service;

import dgsw.pioneers.checkIn.domain.absence.application.domain.model.Absence;
import dgsw.pioneers.checkIn.domain.absence.application.domain.model.enums.AbsenceStatus;
import dgsw.pioneers.checkIn.domain.absence.application.port.out.LoadAbsencePort;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.exception.AttendantNotMatchException;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import dgsw.pioneers.checkIn.domain.attendance.application.port.in.AttendanceUseCase;
import dgsw.pioneers.checkIn.domain.attendance.application.port.out.CreateAttendantPort;
import dgsw.pioneers.checkIn.domain.attendance.application.port.out.LoadAttendancePort;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.LoadLecturePort;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.port.out.LoadMemberPort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import dgsw.pioneers.checkIn.global.lib.zonedatetime.ZoneDateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttendanceService implements AttendanceUseCase {

    private final LoadAttendancePort loadAttendancePort;
    private final LoadLecturePort loadLecturePort;
    private final CreateAttendantPort createAttendantPort;
    private final LoadMemberPort loadMemberPort;
    private final LoadAbsencePort loadAbsencePort;

    @Override
    @Transactional
    public void attendance(Lecture.LectureId lectureId, Member.MemberId memberId) {

        verifyParticipant(lectureId, memberId);

        Attendance attendance = loadAttendancePort.loadAttendanceByLectureAndAttendanceStatusWithAttendants(
                lectureId,
                AttendanceStatus.PERIOD_VALID
        );

        attendance.addAttendant(memberId);
        createAttendantPort.createAttendant(attendance);
    }

    @Override
    @Transactional
    public void attendanceByCode(Lecture.LectureId lectureId, Member.MemberId memberId, String code) {

        verifyParticipant(lectureId, memberId);

        Attendance attendance = loadAttendancePort.loadAttendanceByLectureAndAttendanceStatusWithAttendants(
                lectureId,
                AttendanceStatus.PERIOD_VALID
        );

        attendance.confirmCodeAndAddAttendant(code, memberId);
        createAttendantPort.createAttendant(attendance);
    }

    @Override
    @Transactional
    public void collectiveAttendance(Lecture.LectureId lectureId) {

        Attendance attendance = loadAttendancePort.loadAttendanceByLectureAndAttendanceStatusWithAttendants(
                lectureId,
                AttendanceStatus.PERIOD_VALID
        );

        List<String> absenteeIds = loadAbsencePort.loadAbsencesByLectureIdAndCreatedAtAndAbsenceStatus(lectureId, AbsenceStatus.ABSENCE_ALLOWED, ZoneDateTimeUtil.nowToLocalDate()).stream()
                .map(absence -> absence.getAbsentee().getMemberId().getValue()).toList();

        loadMemberPort.loadNonAttendantsByAttendant(lectureId, attendance.getAttendants(), absenteeIds)
                .forEach(memberId -> {
                    attendance.addAttendant(memberId);
                    createAttendantPort.createAttendant(attendance);
                });
    }

    private void verifyParticipant(Lecture.LectureId lectureId, Member.MemberId memberId) {

        boolean hasMatchingParticipant = loadLecturePort.loadLectureWithParticipants(lectureId).getParticipants()
                .stream().anyMatch(participant -> participant.getParticipantId().equals(memberId));

        if (!hasMatchingParticipant) {
            throw new AttendantNotMatchException();
        }
    }
}
