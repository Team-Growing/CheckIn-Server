package dgsw.pioneers.checkIn.domain.notice.application.domain.service;

import dgsw.pioneers.checkIn.domain.notice.application.domain.model.Notice;
import dgsw.pioneers.checkIn.domain.notice.application.port.in.NoticeDeleteUseCase;
import dgsw.pioneers.checkIn.domain.notice.application.port.out.DeleteNoticePort;
import dgsw.pioneers.checkIn.domain.notice.application.port.out.LoadNoticePort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import dgsw.pioneers.checkIn.global.exception.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeDeleteService implements NoticeDeleteUseCase {

    private final LoadNoticePort loadNoticePort;
    private final DeleteNoticePort deleteNoticePort;

    @Override
    @Transactional
    public void deleteNotice(Notice.NoticeId noticeId) {

        if (!loadNoticePort.existNoticeById(noticeId)) {
            throw new ResourceNotFoundException();
        }

        deleteNoticePort.deleteNotice(noticeId);
    }
}
