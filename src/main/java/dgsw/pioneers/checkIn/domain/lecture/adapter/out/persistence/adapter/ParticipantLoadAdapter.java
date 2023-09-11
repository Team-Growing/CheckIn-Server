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

import java.time.DayOfWeek;
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
                lectureLoadAdapter.loadLectureJpaEntity(lectureId.getValue()), //selectQuery - 1
                memberLoadAdapter.loadMemberJpaEntity(memberId.getValue())); //selectQuery - 2
    }

    public List<LectureToMemberEntity> loadAllLectureByMemberAndLectureStatus(Member.MemberId memberId, LectureStatus lectureStatus) {
        return lectureToMemberRepository.findAllByMemberJpaEntity_IdAndLectureJpaEntity_LectureStatus(
                memberId.getValue(),
                lectureStatus);
    }

    public List<LectureToMemberEntity> loadAllTodayLectureByMemberAndLectureStatus(Member.MemberId memberId, LectureStatus lectureStatus, DayOfWeek dayOfWeek) {
        return lectureToMemberRepository.findAllByMemberJpaEntity_IdAndLectureJpaEntity_LectureStatusAndLectureJpaEntity_LectureSchedule_DayOfWeek(
                memberId.getValue(),
                lectureStatus,
                dayOfWeek);
    }
}
