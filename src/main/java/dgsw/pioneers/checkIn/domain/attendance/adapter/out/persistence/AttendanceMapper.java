package dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate.AttendanceJpaEntity;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.LectureJpaEntity;
import dgsw.pioneers.checkIn.global.annotation.Mapper;

@Mapper
public class AttendanceMapper {

    public AttendanceJpaEntity mapToJpaEntity(Attendance attendance, LectureJpaEntity lecture) {
        return AttendanceJpaEntity.builder()
                .attendanceStatus(attendance.getAttendanceStatus())
                .lectureDate(attendance.getLectureDate())
                .attendStudent(attendance.getAttendStudent())
                .lecture(lecture)
                .code(attendance.getCode())
                .build();
    }
}
