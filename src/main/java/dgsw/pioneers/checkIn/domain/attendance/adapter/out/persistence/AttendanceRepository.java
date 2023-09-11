package dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate.AttendanceJpaEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceJpaEntity, Long> {

    @Query("select a from AttendanceJpaEntity a where a.lecture.id=:lectureId and a.attendanceStatus=:attendanceStatus")
    Optional<AttendanceJpaEntity> selectAttendanceByLectureAndAndAttendanceStatus(Long lectureId, AttendanceStatus attendanceStatus);

    @Query("SELECT a FROM AttendanceJpaEntity a LEFT JOIN FETCH a.attendants att WHERE a.lecture.id = :lectureId AND a.attendanceStatus = :attendanceStatus")
    Optional<AttendanceJpaEntity> selectAttendanceByLectureAndAndAttendanceStatusWithAttendants(Long lectureId, AttendanceStatus attendanceStatus);

    @EntityGraph(attributePaths = {"attendants"})
    Optional<AttendanceJpaEntity> findById(Long attendanceId);
}
