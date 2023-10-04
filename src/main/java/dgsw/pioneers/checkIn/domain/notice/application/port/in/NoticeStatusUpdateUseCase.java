package dgsw.pioneers.checkIn.domain.notice.application.port.in;

import dgsw.pioneers.checkIn.domain.notice.application.domain.model.Notice;
import dgsw.pioneers.checkIn.domain.notice.application.domain.model.enums.NoticeStatus;

public interface NoticeStatusUpdateUseCase {
    void updateNoticeStatus(Notice.NoticeId noticeId, NoticeStatus noticeStatus);
}
