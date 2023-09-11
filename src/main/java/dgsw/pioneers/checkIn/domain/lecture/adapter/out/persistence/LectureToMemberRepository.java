package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.LectureJpaEntity;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.relation.LectureToMemberEntity;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.MemberJpaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface LectureToMemberRepository extends JpaRepository<LectureToMemberEntity, Long> {

    boolean existsByLectureJpaEntityAndMemberJpaEntity(LectureJpaEntity lectureJpa, MemberJpaEntity memberJpaEntity);

    @EntityGraph(attributePaths = {"lectureJpaEntity"})
    List<LectureToMemberEntity> findAllByMemberJpaEntity_IdAndLectureJpaEntity_LectureStatus(
            String memberId, LectureStatus lectureStatus
    );

    @EntityGraph(attributePaths = {"lectureJpaEntity"})
    List<LectureToMemberEntity> findAllByMemberJpaEntity_IdAndLectureJpaEntity_LectureStatusAndLectureJpaEntity_LectureSchedule_DayOfWeek(
            String memberId, LectureStatus lectureStatus, DayOfWeek dayOfWeek);
}
