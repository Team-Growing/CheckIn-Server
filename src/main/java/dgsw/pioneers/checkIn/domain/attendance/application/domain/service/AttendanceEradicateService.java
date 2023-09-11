package dgsw.pioneers.checkIn.domain.attendance.application.domain.service;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendant;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import dgsw.pioneers.checkIn.domain.attendance.application.port.in.AttendanceEradicateUseCase;
import dgsw.pioneers.checkIn.domain.attendance.application.port.out.DeleteAttendantPort;
import dgsw.pioneers.checkIn.domain.attendance.application.port.out.LoadAttendancePort;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttendanceEradicateService implements AttendanceEradicateUseCase {

    private final LoadAttendancePort loadAttendancePort;
    private final DeleteAttendantPort deleteAttendantPort;

    @Override
    @Transactional
    public void eradicate(Lecture.LectureId lectureId, Member.MemberId memberId) {

        Attendance attendance = loadAttendancePort.loadAttendanceByLectureAndAttendanceStatusWithAttendants(
                lectureId, AttendanceStatus.PERIOD_VALID
        );

        Attendant attendant = attendance.eradicateAttendant(memberId);
        deleteAttendantPort.deleteAttendant(attendance.getAttendanceId(), attendant);
    }
}
