package dgsw.pioneers.checkIn.domain.notice.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.notice.adapter.out.persistence.NoticeMapper;
import dgsw.pioneers.checkIn.domain.notice.adapter.out.persistence.NoticeRepository;
import dgsw.pioneers.checkIn.domain.notice.application.domain.model.Notice;
import dgsw.pioneers.checkIn.domain.notice.application.domain.model.enums.NoticeStatus;
import dgsw.pioneers.checkIn.domain.notice.application.port.out.LoadNoticePort;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import dgsw.pioneers.checkIn.global.exception.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class NoticeLoadAdapter implements LoadNoticePort {

    private final NoticeRepository noticeRepository;
    private final NoticeMapper noticeMapper;

    @Override
    public Notice loadNoticeById(Notice.NoticeId noticeId) {
        return noticeMapper.mapToDomainEntity(noticeRepository.findById(noticeId.getValue()).orElseThrow(ResourceNotFoundException::new));
    }

    @Override
    public boolean existNoticeById(Notice.NoticeId noticeId) {
        return noticeRepository.existsById(noticeId.getValue());
    }

    @Override
    public List<Notice> loadAllNotice() {
        return noticeRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(noticeMapper::mapToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public List<Notice> loadAllNoticeByNoticeStatus(NoticeStatus noticeStatus) {
        return noticeRepository.findAllByNoticeStatusOrderByCreatedAtDesc(noticeStatus).stream()
                .map(noticeMapper::mapToDomainEntity).collect(Collectors.toList());
    }
}
