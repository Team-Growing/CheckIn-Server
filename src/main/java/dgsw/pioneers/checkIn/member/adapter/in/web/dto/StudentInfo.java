package dgsw.pioneers.checkIn.member.adapter.in.web.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentInfo {

    int grade;
    int room;
    int number;
}
