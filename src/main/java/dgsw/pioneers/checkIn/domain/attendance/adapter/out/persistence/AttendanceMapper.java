package dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate.AttendanceJpaEntity;
import dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate.AttendantJpaEntity;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendant;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.LectureJpaEntity;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.global.annotation.Mapper;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public class AttendanceMapper {

    public AttendanceJpaEntity mapToJpaEntity(Attendance attendance, LectureJpaEntity lecture) {
        return AttendanceJpaEntity.builder()
                .attendanceStatus(attendance.getAttendanceStatus())
                .attendanceTime(attendance.getAttendanceTime())
                .lectureDate(attendance.getLectureDate())
                .attendStudent(attendance.getAttendStudent())
                .lecture(lecture)
                .code(attendance.getCode())
                .build();
    }

    public Attendance mapToDomainEntity(AttendanceJpaEntity attendanceJpa) {
        return Attendance.withId(
                new Attendance.AttendanceId(attendanceJpa.getId()),
                attendanceJpa.getAttendanceStatus(),
                attendanceJpa.getAttendanceTime(),
                attendanceJpa.getLectureDate(),
                attendanceJpa.getAttendStudent(),
                attendanceJpa.getCode(),
                null,
                null
        );
    }

    public Attendance mapToDomainEntityWithAttendants(AttendanceJpaEntity attendanceJpa) {
        return Attendance.withId(
                new Attendance.AttendanceId(attendanceJpa.getId()),
                attendanceJpa.getAttendanceStatus(),
                attendanceJpa.getAttendanceTime(),
                attendanceJpa.getLectureDate(),
                attendanceJpa.getAttendStudent(),
                attendanceJpa.getCode(),
                null,
                getAttendants(attendanceJpa)
        );
    }

    public Attendance mapToDomainEntityWithLecture(AttendanceJpaEntity attendanceJpa, Long lectureId) { //파라미터로 강좌 아이디를 받는 이유 : Lazy Loading
        return Attendance.withId(
                new Attendance.AttendanceId(attendanceJpa.getId()),
                attendanceJpa.getAttendanceStatus(),
                attendanceJpa.getAttendanceTime(),
                attendanceJpa.getLectureDate(),
                attendanceJpa.getAttendStudent(),
                attendanceJpa.getCode(),
                new Lecture.LectureId(lectureId),
                null
        );
    }

    private List<Attendant> getAttendants(AttendanceJpaEntity attendanceJpa) {
        return attendanceJpa.getAttendants().stream()
                .map(this::attendantMapToDomain).collect(Collectors.toList());
    }

    private Attendant attendantMapToDomain(AttendantJpaEntity attendantJpaEntity) {
        return Attendant.builder()
                .attendantId(new Member.MemberId(attendantJpaEntity.getMemberId().getId()))
                .applyDateTime(attendantJpaEntity.getApplyDateTime())
                .build();
    }
}
