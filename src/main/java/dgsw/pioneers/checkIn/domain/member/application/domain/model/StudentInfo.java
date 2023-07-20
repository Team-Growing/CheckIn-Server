package dgsw.pioneers.checkIn.domain.member.application.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentInfo {

    int year;
    int grade;
    int room;
    int number;
}
