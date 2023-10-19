package dgsw.pioneers.checkIn.domain.absence.application.domain.model;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.StudentInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Absentee {

    Member.MemberId memberId;
    String name;
    StudentInfo studentInfo;
}
