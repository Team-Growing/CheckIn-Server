package dgsw.pioneers.checkIn.domain.lecture.application.port.in;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;

import java.util.List;

public interface MemberLectureLoadUseCase {

    List<Lecture> loadLectureByMember(Member member);

    List<Lecture> loadTodayLectureByMember(Member member);
}
