package dgsw.pioneers.checkIn.lecture.application.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WeekPlan {

    int week;
    String introduction;
}
