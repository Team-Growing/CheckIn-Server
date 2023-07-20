package dgsw.pioneers.checkIn.lecture.application.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LectureSchedule {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull LocalDate startDay;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull LocalDate endDay;

    @NotNull DayOfWeek dayOfWeek;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @NotNull LocalTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    @NotNull LocalTime endTime;
}
