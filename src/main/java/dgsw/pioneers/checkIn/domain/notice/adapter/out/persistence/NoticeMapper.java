package dgsw.pioneers.checkIn.domain.notice.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.notice.adapter.out.persistence.aggregate.NoticeJpaEntity;
import dgsw.pioneers.checkIn.domain.notice.application.domain.model.Notice;
import dgsw.pioneers.checkIn.global.annotation.Mapper;

@Mapper
public class NoticeMapper {

    public NoticeJpaEntity mapToJpaEntity(Notice notice) {
        return NoticeJpaEntity.builder()
                .content(notice.getContent())
                .noticeStatus(notice.getNoticeStatus())
                .createdAt(notice.getCreatedAt())
                .build();
    }

    public Notice mapToDomainEntity(NoticeJpaEntity noticeJpaEntity) {
        return Notice.withId(
                new Notice.NoticeId(noticeJpaEntity.getId()),
                noticeJpaEntity.getNoticeStatus(),
                noticeJpaEntity.getContent(),
                noticeJpaEntity.getCreatedAt()
        );
    }
}
