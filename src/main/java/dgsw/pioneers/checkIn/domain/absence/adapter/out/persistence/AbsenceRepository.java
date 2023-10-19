package dgsw.pioneers.checkIn.domain.absence.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.absence.adapter.out.persistence.aggregate.AbsenceJpaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AbsenceRepository extends JpaRepository<AbsenceJpaEntity, Long> {

    @Query("SELECT a FROM AbsenceJpaEntity a WHERE a.id = :id")
    Optional<AbsenceJpaEntity> selectOne(Long id);

    @EntityGraph(attributePaths = {"memberJpaEntity"})
    Optional<AbsenceJpaEntity> findById(Long id);

    @EntityGraph(attributePaths = {"memberJpaEntity"})
    List<AbsenceJpaEntity> findAllByOrderByCreatedAtDesc();

    boolean existsByAttendanceId_IdAndMemberJpaEntity_Id(Long attendanceId, String memberId);
}
