package dgsw.pioneers.checkIn.domain.lecture.application.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AcceptableStudent {

    @NotNull int maxStudent;
    @NotNull int minStudent;
    @NotNull int targetGrade;
}
