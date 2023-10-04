package dgsw.pioneers.checkIn.domain.notice.application.port.out;

import dgsw.pioneers.checkIn.domain.notice.application.domain.model.Notice;
import dgsw.pioneers.checkIn.domain.notice.application.domain.model.enums.NoticeStatus;

import java.util.List;

public interface LoadNoticePort {

    List<Notice> loadAllNotice();

    List<Notice> loadAllNoticeByNoticeStatus(NoticeStatus noticeStatus);
}
