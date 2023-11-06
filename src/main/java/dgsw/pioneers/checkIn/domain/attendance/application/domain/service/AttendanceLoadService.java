package dgsw.pioneers.checkIn.domain.attendance.application.domain.service;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.exception.AttendanceCodeForbiddenException;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import dgsw.pioneers.checkIn.domain.attendance.application.port.in.AttendanceLoadUseCase;
import dgsw.pioneers.checkIn.domain.attendance.application.port.out.LoadAttendancePort;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.LoadLecturePort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import dgsw.pioneers.checkIn.global.lib.zonedatetime.ZoneDateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttendanceLoadService implements AttendanceLoadUseCase {

    private final LoadAttendancePort loadAttendancePort;
    private final LoadLecturePort loadLecturePort;

    @Override
    public String getAttendanceCode(Lecture.LectureId lectureId) {

        if (loadLecturePort.isLectureActive(lectureId, ZoneDateTimeUtil.nowToLocalTime())) {
            return loadAttendancePort.loadAttendanceByLectureAndAttendanceStatus(
                    lectureId,
                    AttendanceStatus.PERIOD_VALID).getCode();
        } else {
            throw new AttendanceCodeForbiddenException();
        }
    }
}
