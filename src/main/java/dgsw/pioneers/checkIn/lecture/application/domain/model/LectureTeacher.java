package dgsw.pioneers.checkIn.lecture.application.domain.model;

import dgsw.pioneers.checkIn.member.application.domain.model.Member.MemberId;
import lombok.*;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LectureTeacher {

    MemberId memberId;
    String name;
}
