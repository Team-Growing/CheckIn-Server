package dgsw.pioneers.checkIn.domain.absence.application.domain.model;

import dgsw.pioneers.checkIn.domain.absence.application.domain.model.enums.AbsenceStatus;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.global.lib.zonedatetime.ZoneDateTimeUtil;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Absence {

    private final Absence.AbsenceId absenceId;

    private AbsenceStatus absenceStatus;

    private final String reason;

    private final LocalDateTime createdAt;

    private final Lecture.LectureId lectureId;

    private final Attendance.AttendanceId attendanceId;

    private Absentee absentee;

    @Value
    @AllArgsConstructor
    public static class AbsenceId {
        Long value;
    }

    public static Absence withId(
            AbsenceId absenceId,
            AbsenceStatus absenceStatus,
            String reason,
            LocalDateTime createdAt,
            Lecture.LectureId lectureId,
            Attendance.AttendanceId attendanceId,
            Absentee absentee) {
        return new Absence(absenceId, absenceStatus, reason, createdAt, lectureId, attendanceId, absentee);
    }

    @Builder
    public Absence(
            String reason,
            Lecture.LectureId lectureId,
            Attendance.AttendanceId attendanceId,
            Member.MemberId memberId) {
        this.absenceId = null;
        this.absenceStatus = AbsenceStatus.ABSENCE_PENDING;
        this.reason = reason;
        this.createdAt = ZoneDateTimeUtil.nowToLocalDateTime();
        this.lectureId = lectureId;
        this.attendanceId = attendanceId;
        this.absentee = Absentee.builder()
                .memberId(memberId)
                .build();
    }

    public void updateAbsenceStatus(AbsenceStatus absenceStatus) {
        this.absenceStatus = absenceStatus;
    }
}
