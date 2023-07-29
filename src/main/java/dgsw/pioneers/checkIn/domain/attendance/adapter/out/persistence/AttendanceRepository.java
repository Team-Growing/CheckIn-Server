package dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate.AttendanceJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceJpaEntity, Long> {
}
