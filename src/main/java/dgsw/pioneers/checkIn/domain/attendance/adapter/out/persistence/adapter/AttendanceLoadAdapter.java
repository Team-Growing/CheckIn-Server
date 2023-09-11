package dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.AttendanceMapper;
import dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.AttendanceRepository;
import dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate.AttendanceJpaEntity;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import dgsw.pioneers.checkIn.domain.attendance.application.port.out.LoadAttendancePort;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import dgsw.pioneers.checkIn.global.exception.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class AttendanceLoadAdapter implements LoadAttendancePort {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;

    @Override
    public Attendance loadAttendanceByLectureAndAttendanceStatus(Lecture.LectureId lectureId, AttendanceStatus attendanceStatus) {

        AttendanceJpaEntity attendanceJpaEntity = attendanceRepository.selectAttendanceByLectureAndAndAttendanceStatus(
                lectureId.getValue(), attendanceStatus
        ).orElseThrow(ResourceNotFoundException::new);

        return attendanceMapper.mapToDomainEntity(attendanceJpaEntity, lectureId.getValue());
    }

    @Override
    public Attendance loadAttendanceByLectureAndAttendanceStatusWithAttendants(Lecture.LectureId lectureId, AttendanceStatus attendanceStatus) {

        AttendanceJpaEntity attendanceJpaEntity = attendanceRepository.selectAttendanceByLectureAndAndAttendanceStatusWithAttendants(
                lectureId.getValue(), attendanceStatus
        ).orElseThrow(ResourceNotFoundException::new);

        return attendanceMapper.mapToDomainEntity(attendanceJpaEntity, lectureId.getValue());
    }
}
