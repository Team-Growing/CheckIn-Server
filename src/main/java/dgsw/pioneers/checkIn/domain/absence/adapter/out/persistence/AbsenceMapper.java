package dgsw.pioneers.checkIn.domain.absence.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.absence.adapter.out.persistence.aggregate.AbsenceJpaEntity;
import dgsw.pioneers.checkIn.domain.absence.application.domain.model.Absence;
import dgsw.pioneers.checkIn.domain.absence.application.domain.model.Absentee;
import dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate.vo.AttendanceIdJpaVO;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo.LectureIdJpaVO;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.MemberJpaEntity;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.StudentInfo;
import dgsw.pioneers.checkIn.global.annotation.Mapper;

@Mapper
public class AbsenceMapper {

    public AbsenceJpaEntity mapToJpaEntity(Absence absence, MemberJpaEntity memberJpaEntity) {
        return AbsenceJpaEntity.builder()
                .absenceStatus(absence.getAbsenceStatus())
                .reason(absence.getReason())
                .createdAt(absence.getCreatedAt())
                .lectureId(new LectureIdJpaVO(absence.getLectureId().getValue()))
                .attendanceId(new AttendanceIdJpaVO(absence.getAttendanceId().getValue()))
                .memberJpaEntity(memberJpaEntity)
                .build();
    }

    public Absence mapToDomainEntity(AbsenceJpaEntity absenceJpaEntity) {
        return Absence.withId(
                new Absence.AbsenceId(absenceJpaEntity.getId()),
                absenceJpaEntity.getAbsenceStatus(),
                absenceJpaEntity.getReason(),
                absenceJpaEntity.getCreatedAt(),
                new Lecture.LectureId(absenceJpaEntity.getLectureId().getId()),
                new Attendance.AttendanceId(absenceJpaEntity.getAttendanceId().getId()),
                Absentee.builder()
                        .memberId(new Member.MemberId(absenceJpaEntity.getMemberJpaEntity().getId()))
                        .name(absenceJpaEntity.getMemberJpaEntity().getName())
                        .studentInfo(
                                StudentInfo.builder()
                                        .grade(absenceJpaEntity.getMemberJpaEntity().getStudentInfo().getGrade())
                                        .room(absenceJpaEntity.getMemberJpaEntity().getStudentInfo().getRoom())
                                        .number(absenceJpaEntity.getMemberJpaEntity().getStudentInfo().getNumber())
                                        .build()
                        )
                        .build()
        );
    }
}
