package dgsw.pioneers.checkIn.global.batch.step.dto;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture.LectureId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AttendanceJobDto implements Serializable {

    private AttendanceStatus attendanceStatus;
    private DayOfWeek dayOfWeek;
    private LectureId lectureId;
    private LocalDate lectureDate;

    public AttendanceJobDto(Lecture lecture) {
        new AttendanceJobDto(AttendanceStatus.PERIOD_EXPIRED, lecture.getLectureSchedule().getDayOfWeek(), lecture.getLectureId(), null);
    }

    public void setLectureDate(LocalDate lectureDate) {
        this.lectureDate = lectureDate;
    }

    public Attendance mapToDomainEntity() {
        return Attendance.builder()
                .attendanceStatus(attendanceStatus)
                .lectureDate(lectureDate)
                .lectureId(lectureId).build();
    }
}
