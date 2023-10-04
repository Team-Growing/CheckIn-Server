package dgsw.pioneers.checkIn.domain.notice.application.domain.model;

import dgsw.pioneers.checkIn.domain.notice.application.domain.model.enums.NoticeStatus;
import dgsw.pioneers.checkIn.global.lib.zonedatetime.ZoneDateTimeUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Notice {

    private final NoticeId noticeId;

    private NoticeStatus noticeStatus;

    private final String content;

    private final LocalDateTime createdAt;

    @Value
    @AllArgsConstructor
    public static class NoticeId {
        Long value;
    }

    public static Notice withId(
            Notice.NoticeId noticeId,
            NoticeStatus noticeStatus,
            String content,
            LocalDateTime createdAt) {
        return new Notice(noticeId, noticeStatus, content, createdAt);
    }

    public static Notice generate(String content) {
        return new Notice(null, NoticeStatus.ACTIVE, content, ZoneDateTimeUtil.nowToLocalDateTime());
    }

    public void updateNoticeStatus(NoticeStatus noticeStatus) {
        this.noticeStatus = noticeStatus;
    }
}
