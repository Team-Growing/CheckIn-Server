package dgsw.pioneers.checkIn.domain.attendance.application.domain.service;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import dgsw.pioneers.checkIn.domain.attendance.application.port.in.AttendanceUseCase;
import dgsw.pioneers.checkIn.domain.attendance.application.port.out.CreateAttendantPort;
import dgsw.pioneers.checkIn.domain.attendance.application.port.out.LoadAttendancePort;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttendanceService implements AttendanceUseCase {

    private final LoadAttendancePort loadAttendancePort;
    private final CreateAttendantPort createAttendantPort;

    @Override
    @Transactional
    public void attendance(Lecture.LectureId lectureId, Member.MemberId memberId, String code) {

        Attendance attendance = loadAttendancePort.loadAttendanceByLectureAndAttendanceStatusWithAttendants(
                lectureId,
                AttendanceStatus.PERIOD_VALID
        );

        attendance.addAttendant(code, memberId);
        createAttendantPort.createAttendant(attendance);
    }
}
