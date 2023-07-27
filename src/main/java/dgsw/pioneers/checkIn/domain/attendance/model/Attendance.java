package dgsw.pioneers.checkIn.domain.attendance.model;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member.MemberId;
import lombok.*;

import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Attendance {

    private final AttendanceId attendanceId;

    private LocalDate lectureDate;

    private int attendStudent;

    private MemberId memberId;

    @Value
    @AllArgsConstructor
    public static class AttendanceId {
        Long value;
    }

    public static Attendance withId(
            AttendanceId lectureId,
            LocalDate lectureDate,
            int attendStudent,
            MemberId memberId) {
        return new Attendance(lectureId, lectureDate, attendStudent, memberId);
    }
}
