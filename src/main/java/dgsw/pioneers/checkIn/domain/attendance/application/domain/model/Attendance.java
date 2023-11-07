package dgsw.pioneers.checkIn.domain.attendance.application.domain.model;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.exception.AttendanceCodeNotMatchException;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.exception.AttendanceDuplicatedException;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.exception.AttendantNotFoundException;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceTime;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture.LectureId;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Attendance {

    private final AttendanceId attendanceId;

    private AttendanceStatus attendanceStatus;

    private final AttendanceTime attendanceTime;

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
            AttendanceTime attendanceTime,
            LocalDate lectureDate,
            int attendStudent,
            String code,
            LectureId lectureId,
            List<Attendant> attendants) {
        return new Attendance(attendanceId, attendanceStatus, attendanceTime, lectureDate, attendStudent, code, lectureId, attendants);
    }

    @Builder
    public Attendance(
            AttendanceStatus attendanceStatus,
            AttendanceTime attendanceTime,
            LectureId lectureId,
            LocalDate lectureDate) {
        this.attendanceId = null;
        this.attendanceStatus = attendanceStatus;
        this.attendanceTime = attendanceTime;
        this.lectureDate = lectureDate;
        this.attendStudent = 0;
        this.lectureId = lectureId;
    }

    public void updateCode(String code) {
        this.code = code;
    }

    public void confirmCodeAndAddAttendant(String reqCode, Member.MemberId memberId) {

        if (!this.code.equals(reqCode)) {
            throw new AttendanceCodeNotMatchException();
        }

        this.addAttendant(memberId);
    }

    public void addAttendant(Member.MemberId memberId) {

        Optional.ofNullable(this.attendants).ifPresentOrElse(
                att -> {
                    this.attendants.forEach(attendant -> {
                        if (attendant.getAttendantId().equals(memberId)) throw new AttendanceDuplicatedException();
                    });
                },
                () -> {
                    this.attendants = new ArrayList<>();
                }
        );

        this.attendants.add(Attendant.generate(memberId));
        this.attendStudent++;
    }

    public Attendant eradicateAttendant(Member.MemberId memberId) {
        return findAttendant(memberId)
                .orElseThrow(AttendantNotFoundException::new);
    }

    private Optional<Attendant> findAttendant(Member.MemberId memberId) {
        return attendants.stream()
                .filter(attendant -> attendant.getAttendantId().equals(memberId))
                .findFirst();
    }

    public void activate() {
        this.attendanceStatus = AttendanceStatus.PERIOD_VALID;
    }

    public void deactivate() {
        this.attendanceStatus = AttendanceStatus.PERIOD_EXPIRED_AFTER;
    }
}
