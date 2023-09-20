package dgsw.pioneers.checkIn.domain.lecture.application.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AcceptableStudent {

    @NotNull
    int maxStudent;

    @Min(value = 1)
    @NotNull
    int minStudent;

    @Min(1) @Max(2)
    @NotNull
    int targetGrade;
}
