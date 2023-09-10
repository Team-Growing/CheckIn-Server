package dgsw.pioneers.checkIn.domain.attendance.application.domain.model;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.exception.AttendanceCodeNotMatchException;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.exception.AttendanceDuplicatedException;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture.LectureId;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Attendance {

    private final AttendanceId attendanceId;

    private final AttendanceStatus attendanceStatus;

    private final LocalDate lectureDate;

    private int attendStudent; //출석한 학생수

    private String code;

    private final LectureId lectureId;

    private List<Attendant> attendants;

    @Value
    @AllArgsConstructor
    public static class AttendanceId {
        Long value;
    }

    public static Attendance withId(
            AttendanceId attendanceId,
            AttendanceStatus attendanceStatus,
            LocalDate lectureDate,
            int attendStudent,
            String code,
            LectureId lectureId,
            List<Attendant> attendants) {
        return new Attendance(attendanceId, attendanceStatus, lectureDate, attendStudent, code, lectureId, attendants);
    }

    @Builder
    public Attendance(
            AttendanceStatus attendanceStatus,
            LectureId lectureId,
            LocalDate lectureDate) {
        this.attendanceId = null;
        this.attendanceStatus = attendanceStatus;
        this.lectureDate = lectureDate;
        this.attendStudent = 0;
        this.lectureId = lectureId;
    }

    public void updateCode(String code) {
        this.code = code;
    }

    public void addAttendant(String reqCode, Member.MemberId memberId) {

        if (!this.code.equals(reqCode)) {
            throw new AttendanceCodeNotMatchException();
        }

        this.attendants.forEach(attendant -> {
            if (attendant.getAttendantId().equals(memberId)) throw new AttendanceDuplicatedException();
        });

        this.attendants.add(Attendant.generate(memberId));
        this.attendStudent++;
    }
}
