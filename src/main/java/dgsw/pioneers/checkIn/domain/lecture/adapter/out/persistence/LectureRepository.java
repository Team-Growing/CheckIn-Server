package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.LectureJpaEntity;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Repository
public interface LectureRepository extends JpaRepository<LectureJpaEntity, Long> {

    @Query("SELECT DISTINCT l FROM LectureJpaEntity l JOIN FETCH l.dayOfWeekVO d WHERE l.id = :lectureId")
    Optional<LectureJpaEntity> findById(Long lectureId);

    @Query("SELECT l FROM LectureJpaEntity l JOIN FETCH l.dayOfWeekVO d LEFT JOIN FETCH l.weekPlans WHERE l.id = :lectureId")
    Optional<LectureJpaEntity> findByIdWithWeekPlans(Long lectureId);

    @Query("SELECT DISTINCT l FROM LectureJpaEntity l JOIN FETCH l.dayOfWeekVO d LEFT JOIN FETCH l.participants WHERE l.id = :lectureId")
    Optional<LectureJpaEntity> selectByIdWithParticipants(Long lectureId);

    @Query("SELECT l FROM LectureJpaEntity l LEFT JOIN FETCH l.dayOfWeekVO d WHERE l.lectureStatus = :lectureStatus ORDER BY l.lectureName")
    List<LectureJpaEntity> findAllByLectureStatus(LectureStatus lectureStatus);

    @Query("SELECT DISTINCT l FROM LectureJpaEntity l JOIN FETCH l.dayOfWeekVO d WHERE l.lectureStatus = :lectureStatus AND l.acceptableStudent.targetGrade = :targetGrade ORDER BY l.lectureName")
    List<LectureJpaEntity> findAllByLectureStatusAndAcceptableStudent_TargetGrade(LectureStatus lectureStatus, int targetGrade);

    @Query("SELECT l FROM LectureJpaEntity l JOIN FETCH l.dayOfWeekVO d WHERE l.lectureStatus = :lectureStatus AND d.dayOfWeek = :dayOfWeek")
    List<LectureJpaEntity> findAllByLectureStatusAndDayOfWeekVO(LectureStatus lectureStatus, DayOfWeek dayOfWeek);
}
