package dgsw.pioneers.checkIn.domain.notice.application.domain.service;

import dgsw.pioneers.checkIn.domain.notice.application.domain.model.Notice;
import dgsw.pioneers.checkIn.domain.notice.application.domain.model.enums.NoticeStatus;
import dgsw.pioneers.checkIn.domain.notice.application.port.in.NoticeStatusUpdateUseCase;
import dgsw.pioneers.checkIn.domain.notice.application.port.out.LoadNoticePort;
import dgsw.pioneers.checkIn.domain.notice.application.port.out.UpdateNoticeStatusPort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeStatusUpdateService implements NoticeStatusUpdateUseCase {

    private final LoadNoticePort loadNoticePort;
    private final UpdateNoticeStatusPort updateNoticeStatusPort;

    @Override
    @Transactional
    public void updateNoticeStatus(Notice.NoticeId noticeId, NoticeStatus noticeStatus) {

        Notice notice = loadNoticePort.loadNoticeById(noticeId);
        notice.updateNoticeStatus(noticeStatus);

        updateNoticeStatusPort.updateNoticeStatus(notice);
    }
}
