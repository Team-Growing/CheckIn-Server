package dgsw.pioneers.checkIn.domain.member.application.domain.service;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.LoadLectureByParticipantPort;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.port.in.MemberLectureLoadUseCase;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberLectureLoadService implements MemberLectureLoadUseCase {

    private final LoadLectureByParticipantPort loadLectureByParticipantPort;

    @Override
    public List<Lecture> loadLecture(Member.MemberId memberId) {
        return loadLectureByParticipantPort.loadAllLectureByMemberAndStatus(memberId, LectureStatus.COURSE_PERIOD);
    }
}
