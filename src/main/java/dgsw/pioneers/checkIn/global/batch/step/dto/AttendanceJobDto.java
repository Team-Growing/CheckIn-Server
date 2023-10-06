package dgsw.pioneers.checkIn.global.batch.step.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceTime;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture.LectureId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AttendanceJobDto implements Serializable {

    private AttendanceStatus attendanceStatus;
    private LocalTime endTime;
    @Setter
    private AttendanceTime attendanceTime;
    private DayOfWeek dayOfWeek;
    private LectureId lectureId;

    @Setter
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate lectureDate;

    public AttendanceJobDto(LectureId lectureId, LocalTime endTime, DayOfWeek dayOfWeek) {
       this(AttendanceStatus.PERIOD_EXPIRED_BEFORE, endTime, null, dayOfWeek, lectureId, null);
    }

    public Attendance mapToDomainEntity() {
        return Attendance.builder()
                .attendanceStatus(attendanceStatus)
                .attendanceTime(attendanceTime)
                .lectureDate(lectureDate)
                .lectureId(lectureId).build();
    }
}
