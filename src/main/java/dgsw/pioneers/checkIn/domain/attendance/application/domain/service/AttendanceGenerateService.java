package dgsw.pioneers.checkIn.domain.attendance.application.domain.service;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;
import dgsw.pioneers.checkIn.domain.attendance.application.port.in.AttendanceGenerateUseCase;
import dgsw.pioneers.checkIn.domain.attendance.application.port.out.CreateAttendancePort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttendanceGenerateService implements AttendanceGenerateUseCase {

    private final CreateAttendancePort createAttendancePort;

    @Transactional
    @Override
    public void generateAttendance(Attendance attendance) {
        createAttendancePort.createAttendance(attendance);
    }
}
