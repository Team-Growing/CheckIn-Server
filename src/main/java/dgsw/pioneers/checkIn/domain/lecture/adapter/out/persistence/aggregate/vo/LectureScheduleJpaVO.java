package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureScheduleJpaVO {

    private LocalDate startDay;

    private LocalDate EndDay;

    private LocalTime startTime;

    private LocalTime endTime;
}
