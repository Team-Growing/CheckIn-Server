package dgsw.pioneers.checkIn.domain.lecture.application.domain.model;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member.MemberId;
import lombok.*;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LectureTeacher {

    MemberId teacherId;
    String name;
}
