package dgsw.pioneers.checkIn.domain.notice.application.port.in;

import dgsw.pioneers.checkIn.domain.notice.application.domain.model.Notice;

import java.util.List;

public interface NoticeLoadUseCase {

    List<Notice> loadAllNotice();

    List<Notice> loadActiveNotice();
}
