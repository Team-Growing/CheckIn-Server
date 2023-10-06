package dgsw.pioneers.checkIn.domain.attendance.application.domain.service;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceTime;
import dgsw.pioneers.checkIn.domain.attendance.application.port.in.AttendanceActivateUseCase;
import dgsw.pioneers.checkIn.domain.attendance.application.port.out.LoadAttendancePort;
import dgsw.pioneers.checkIn.domain.attendance.application.port.out.UpdateAttendanceStatusPort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import dgsw.pioneers.checkIn.global.lib.zonedatetime.ZoneDateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttendanceActivateService implements AttendanceActivateUseCase {

    private final LoadAttendancePort loadAttendancePort;
    private final UpdateAttendanceStatusPort updateAttendanceStatusPort;

    @Override
    @Transactional
    public void activate(AttendanceTime attendanceTime) {

        List<Attendance> attendances = loadAttendancePort.loadAttendanceByLectureDateAndAttendanceTime(ZoneDateTimeUtil.nowToLocalDate(), attendanceTime);
        attendances.forEach(Attendance::activate);

        attendances.forEach(updateAttendanceStatusPort::updateAttendanceStatus);
    }

    @Override
    @Transactional
    public void deactivate(AttendanceTime attendanceTime) {

        List<Attendance> attendances = loadAttendancePort.loadAttendanceByLectureDateAndAttendanceTime(ZoneDateTimeUtil.nowToLocalDate(), attendanceTime);
        attendances.forEach(Attendance::deactivate);

        attendances.forEach(updateAttendanceStatusPort::updateAttendanceStatus);
    }
}
