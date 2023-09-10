package dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate.AttendanceJpaEntity;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.LectureJpaEntity;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
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

    public Attendance mapToDomainEntity(AttendanceJpaEntity attendanceJpa, Long lectureId) { //파라미터로 강좌 아이디를 받는 이유 : Lazy Loading
        return Attendance.withId(
                new Attendance.AttendanceId(attendanceJpa.getId()),
                attendanceJpa.getAttendanceStatus(),
                attendanceJpa.getLectureDate(),
                attendanceJpa.getAttendStudent(),
                attendanceJpa.getCode(),
                new Lecture.LectureId(lectureId),
                null
        );
    }
}
