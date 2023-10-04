package dgsw.pioneers.checkIn.domain.notice.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.notice.adapter.out.persistence.aggregate.NoticeJpaEntity;
import dgsw.pioneers.checkIn.domain.notice.application.domain.model.enums.NoticeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeJpaEntity, Long> {

    List<NoticeJpaEntity> findAllByOrderByCreatedAtDesc();

    List<NoticeJpaEntity> findAllByNoticeStatusOrderByCreatedAtDesc(NoticeStatus noticeStatus);
}
