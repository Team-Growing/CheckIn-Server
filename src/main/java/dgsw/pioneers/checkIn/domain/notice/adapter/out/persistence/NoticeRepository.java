package dgsw.pioneers.checkIn.domain.notice.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.notice.adapter.out.persistence.aggregate.NoticeJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeJpaEntity, Long> {
}
