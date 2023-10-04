package dgsw.pioneers.checkIn.domain.notice.application.port.out;

import dgsw.pioneers.checkIn.domain.notice.application.domain.model.Notice;

public interface DeleteNoticePort {

    void deleteNotice(Notice.NoticeId noticeId);
}
