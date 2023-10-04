package dgsw.pioneers.checkIn.domain.notice.application.domain.service;

import dgsw.pioneers.checkIn.domain.notice.application.domain.model.Notice;
import dgsw.pioneers.checkIn.domain.notice.application.port.in.NoticeGenerateUseCase;
import dgsw.pioneers.checkIn.domain.notice.application.port.out.CreateNoticePort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NoticeGenerateService implements NoticeGenerateUseCase {

    private final CreateNoticePort createNoticePort;

    @Override
    @Transactional
    public void generateNotice(Notice notice) {
        createNoticePort.createNotice(notice);
    }
}
