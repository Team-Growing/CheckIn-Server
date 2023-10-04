package dgsw.pioneers.checkIn.domain.notice.application.port.in;

import dgsw.pioneers.checkIn.domain.notice.application.domain.model.Notice;

public interface NoticeGenerateUseCase {
    void generateNotice(Notice notice);
}
