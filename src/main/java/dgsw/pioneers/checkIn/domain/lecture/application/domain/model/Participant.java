package dgsw.pioneers.checkIn.domain.lecture.application.domain.model;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Participant {

    LocalDateTime applyDateTime;
    Member.MemberId participantId;
}
