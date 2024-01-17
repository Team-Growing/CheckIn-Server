package dgsw.pioneers.checkIn.domain.lecture.adapter.in.web.dto.res;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.res.MemberInfoRes;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LectureWithMyInfoRes {

    private MemberInfoRes info;
    private List<Lecture> lectures;
}
