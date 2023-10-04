package dgsw.pioneers.checkIn.domain.notice.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.notice.adapter.out.persistence.NoticeMapper;
import dgsw.pioneers.checkIn.domain.notice.application.domain.model.Notice;
import dgsw.pioneers.checkIn.domain.notice.application.port.out.CreateNoticePort;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class NoticePersistenceAdapter implements CreateNoticePort {

    private final NoticeMapper noticeMapper;

    @Override
    public void createNotice(Notice notice) {

    }
}
