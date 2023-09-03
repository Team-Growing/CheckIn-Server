package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.LectureToMemberRepository;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.relation.LectureToMemberEntity;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.LoadParticipantPort;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.adapter.MemberLoadAdapter;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class ParticipantLoadAdapter implements LoadParticipantPort {

    private final LectureToMemberRepository lectureToMemberRepository;
    private final MemberLoadAdapter memberLoadAdapter;
    private final LectureLoadAdapter lectureLoadAdapter;

    @Override
    public boolean existByLectureIdAndMemberId(Lecture.LectureId lectureId, Member.MemberId memberId) {
        return lectureToMemberRepository.existsByLectureJpaEntityAndMemberJpaEntity(
                lectureLoadAdapter.loadLectureJpaEntity(lectureId.getValue()),
                memberLoadAdapter.loadMemberJpaEntity(memberId.getValue()));
    }

    public List<LectureToMemberEntity> loadAllLectureByMemberAndLectureStatus(Member.MemberId memberId, LectureStatus lectureStatus) {
        return lectureToMemberRepository.findAllByMemberJpaEntityAndLectureJpaEntity_LectureStatus(
                memberLoadAdapter.loadMemberJpaEntity(memberId.getValue()),
                lectureStatus);
    }
}
