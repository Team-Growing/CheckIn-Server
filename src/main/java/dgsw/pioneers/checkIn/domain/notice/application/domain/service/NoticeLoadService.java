package dgsw.pioneers.checkIn.domain.notice.application.domain.service;

import dgsw.pioneers.checkIn.domain.notice.application.domain.model.Notice;
import dgsw.pioneers.checkIn.domain.notice.application.domain.model.enums.NoticeStatus;
import dgsw.pioneers.checkIn.domain.notice.application.port.in.NoticeLoadUseCase;
import dgsw.pioneers.checkIn.domain.notice.application.port.out.LoadNoticePort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeLoadService implements NoticeLoadUseCase {

    private final LoadNoticePort loadNoticePort;

    @Override
    public List<Notice> loadAllNotice() {
        return loadNoticePort.loadAllNotice();
    }

    @Override
    public List<Notice> loadActiveNotice() {
        return loadNoticePort.loadAllNoticeByNoticeStatus(NoticeStatus.ACTIVE);
    }
}
