package dgsw.pioneers.checkIn.domain.notice.application.port.out;

import dgsw.pioneers.checkIn.domain.notice.application.domain.model.Notice;

public interface CreateNoticePort {

    void createNotice(Notice notice);
}
