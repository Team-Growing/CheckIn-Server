package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.LectureJpaEntity;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<LectureJpaEntity, Long> {

    @EntityGraph(attributePaths = "weekPlans")
    Optional<LectureJpaEntity> findById(Long lectureId);

    @Query("SELECT DISTINCT l FROM LectureJpaEntity l LEFT JOIN FETCH l.participants WHERE l.id = :lectureId")
    Optional<LectureJpaEntity> selectByIdWithParticipants(Long lectureId);

    List<LectureJpaEntity> findAllByLectureStatus(LectureStatus lectureStatus);

    List<LectureJpaEntity> findAllByLectureStatusAndLectureScheduleDayOfWeek(LectureStatus lectureStatus, DayOfWeek dayOfWeek);
}
