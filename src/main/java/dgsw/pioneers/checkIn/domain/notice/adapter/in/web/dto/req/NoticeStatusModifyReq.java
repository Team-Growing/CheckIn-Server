package dgsw.pioneers.checkIn.domain.notice.adapter.in.web.dto.req;

import dgsw.pioneers.checkIn.domain.notice.application.domain.model.enums.NoticeStatus;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class NoticeStatusModifyReq {

    @NotNull
    private NoticeStatus noticeStatus;
}
