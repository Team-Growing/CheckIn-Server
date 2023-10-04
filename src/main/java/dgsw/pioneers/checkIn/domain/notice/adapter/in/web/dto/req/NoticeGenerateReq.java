package dgsw.pioneers.checkIn.domain.notice.adapter.in.web.dto.req;

import dgsw.pioneers.checkIn.domain.notice.application.domain.model.Notice;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class NoticeGenerateReq {

    @NotBlank
    private String content;

    public Notice mapToDomainEntity() {
        return Notice.generate(this.content);
    }
}
