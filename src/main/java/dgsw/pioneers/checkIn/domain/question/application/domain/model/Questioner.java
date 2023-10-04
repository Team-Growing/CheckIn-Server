package dgsw.pioneers.checkIn.domain.question.application.domain.model;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Questioner {

    Member.MemberId memberId;
    String name;
}
