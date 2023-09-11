package dgsw.pioneers.checkIn.domain.attendance.application.domain.service;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import dgsw.pioneers.checkIn.domain.attendance.application.port.in.AttendanceCodeReissueUseCase;
import dgsw.pioneers.checkIn.domain.attendance.application.port.out.LoadAttendancePort;
import dgsw.pioneers.checkIn.domain.attendance.application.port.out.UpdateAttendanceCodePort;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import dgsw.pioneers.checkIn.global.lib.random.RandomGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttendanceCodeReissueService implements AttendanceCodeReissueUseCase {

    private final LoadAttendancePort loadAttendancePort;
    private final UpdateAttendanceCodePort updateAttendanceCodePort;

    @Transactional
    @Override
    public void reissueCode(Lecture.LectureId lectureId) {

        Attendance attendance = loadAttendancePort.loadAttendanceByLectureAndAttendanceStatus(
                lectureId,
                AttendanceStatus.PERIOD_VALID);

        attendance.updateCode(RandomGenerator.generate());
        updateAttendanceCodePort.updateAttendanceCode(attendance);
    }
}
