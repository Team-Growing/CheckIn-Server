package dgsw.pioneers.checkIn.domain.notice.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.notice.adapter.out.persistence.NoticeMapper;
import dgsw.pioneers.checkIn.domain.notice.adapter.out.persistence.NoticeRepository;
import dgsw.pioneers.checkIn.domain.notice.adapter.out.persistence.aggregate.NoticeJpaEntity;
import dgsw.pioneers.checkIn.domain.notice.application.domain.model.Notice;
import dgsw.pioneers.checkIn.domain.notice.application.port.out.CreateNoticePort;
import dgsw.pioneers.checkIn.domain.notice.application.port.out.DeleteNoticePort;
import dgsw.pioneers.checkIn.domain.notice.application.port.out.UpdateNoticeStatusPort;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class NoticePersistenceAdapter implements CreateNoticePort, DeleteNoticePort, UpdateNoticeStatusPort {

    private final NoticeRepository noticeRepository;
    private final NoticeMapper noticeMapper;

    @Override
    public void createNotice(Notice notice) {
        noticeRepository.save(noticeMapper.mapToJpaEntity(notice));
    }

    @Override
    public void deleteNotice(Notice.NoticeId noticeId) {
        noticeRepository.deleteById(noticeId.getValue());
    }

    @Override
    public void updateNoticeStatus(Notice notice) {

        NoticeJpaEntity noticeJpa = noticeRepository.findById(notice.getNoticeId().getValue()).get();
        noticeJpa.updateNoticeStatus(notice.getNoticeStatus());
    }
}
