package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.LectureJpaEntity;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.relation.LectureToMemberEntity;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.MemberJpaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureToMemberRepository extends JpaRepository<LectureToMemberEntity, Long> {

    boolean existsByLectureJpaEntityAndMemberJpaEntity(LectureJpaEntity lectureJpa, MemberJpaEntity memberJpaEntity);

    @EntityGraph(attributePaths = {"memberJpaEntity", "lectureJpaEntity"})
    List<LectureToMemberEntity> findAllByMemberJpaEntityAndLectureJpaEntity_LectureStatus(MemberJpaEntity memberJpaEntity, LectureStatus lectureStatus);
}
