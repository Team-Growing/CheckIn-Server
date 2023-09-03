package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.LectureMapper;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.LoadLectureByParticipantPort;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class LectureByParticipantAdapter implements LoadLectureByParticipantPort {

    private final ParticipantLoadAdapter participantLoadAdapter;
    private final LectureMapper lectureMapper;

    @Override
    public List<Lecture> loadAllLectureByMemberAndStatus(Member.MemberId memberId, LectureStatus lectureStatus) {

        return participantLoadAdapter.loadAllLectureByMemberAndLectureStatus(memberId, lectureStatus)
                .stream().map(lectureToMember -> lectureMapper.mapToDomainEntity(lectureToMember.getLectureJpaEntity()))
                .collect(Collectors.toList());
    }
}
