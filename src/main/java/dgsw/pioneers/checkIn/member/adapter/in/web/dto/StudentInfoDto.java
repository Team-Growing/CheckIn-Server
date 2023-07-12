package dgsw.pioneers.checkIn.member.adapter.in.web.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentInfoDto {

    @Positive
    int grade;
    @Positive
    int room;
    @Positive
    int number;
}
