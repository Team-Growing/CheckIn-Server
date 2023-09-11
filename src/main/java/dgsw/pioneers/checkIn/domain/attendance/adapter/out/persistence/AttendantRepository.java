package dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate.AttendantJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendantRepository extends JpaRepository<AttendantJpaEntity, Long> {

    void deleteByMemberId_IdAndAttendanceJpaEntity_Id(String memberId, Long attendanceId);
}
