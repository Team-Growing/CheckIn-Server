package dgsw.pioneers.checkIn.domain.attendance.application.domain.service;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import dgsw.pioneers.checkIn.domain.attendance.application.port.in.AttendanceLoadUseCase;
import dgsw.pioneers.checkIn.domain.attendance.application.port.out.LoadAttendancePort;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttendanceLoadService implements AttendanceLoadUseCase {

    private final LoadAttendancePort loadAttendancePort;

    @Override
    public String getAttendanceCode(Lecture.LectureId lectureId) {

        return loadAttendancePort.loadAttendanceByLectureAndAttendanceStatus(
                lectureId,
                AttendanceStatus.PERIOD_VALID).getCode();
    }
}
