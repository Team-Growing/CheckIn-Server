package dgsw.pioneers.checkIn.domain.absence.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.absence.adapter.out.persistence.aggregate.AbsenceJpaEntity;
import dgsw.pioneers.checkIn.domain.absence.application.domain.model.enums.AbsenceStatus;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AbsenceRepository extends JpaRepository<AbsenceJpaEntity, Long> {

    @Query("SELECT a FROM AbsenceJpaEntity a WHERE a.id = :id")
    Optional<AbsenceJpaEntity> selectOne(Long id);

    @EntityGraph(attributePaths = {"memberJpaEntity"})
    Optional<AbsenceJpaEntity> findById(Long id);

    @Query("SELECT a FROM AbsenceJpaEntity a LEFT JOIN FETCH a.memberJpaEntity m WHERE DATE(a.createdAt) = DATE(:date) ORDER BY a.createdAt DESC")
    List<AbsenceJpaEntity> findAllByCreatedAtOrderByCreatedAtDesc(LocalDate date);

    //JPQL 은 메개변수를 자동으로 변환해주지 않는다. 그러므로 LocalDate -> Date 변환을 해주어야 한다.
    @Query("SELECT a FROM AbsenceJpaEntity a WHERE a.memberJpaEntity.id = :id AND DATE(a.createdAt) = DATE(:now)")
    List<AbsenceJpaEntity> findAllByMemberJpaEntity_IdAndCreatedAt(String id, LocalDate now);

    @Query("SELECT a FROM AbsenceJpaEntity a LEFT JOIN FETCH a.memberJpaEntity m WHERE a.lectureId.id = :id AND DATE(a.createdAt) = DATE(:date) AND a.absenceStatus = :absenceAllowed")
    List<AbsenceJpaEntity> findAllByLectureIdAndCreatedAtAndAbsenceStatus(Long id, AbsenceStatus absenceAllowed, LocalDate date);

    boolean existsByAttendanceId_IdAndMemberJpaEntity_Id(Long attendanceId, String memberId);
}
