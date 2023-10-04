package dgsw.pioneers.checkIn.domain.notice.adapter.in.web;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole;
import dgsw.pioneers.checkIn.domain.notice.adapter.in.web.dto.req.NoticeGenerateReq;
import dgsw.pioneers.checkIn.domain.notice.adapter.in.web.dto.req.NoticeStatusModifyReq;
import dgsw.pioneers.checkIn.domain.notice.application.domain.model.Notice;
import dgsw.pioneers.checkIn.domain.notice.application.port.in.NoticeDeleteUseCase;
import dgsw.pioneers.checkIn.domain.notice.application.port.in.NoticeGenerateUseCase;
import dgsw.pioneers.checkIn.domain.notice.application.port.in.NoticeLoadUseCase;
import dgsw.pioneers.checkIn.domain.notice.application.port.in.NoticeStatusUpdateUseCase;
import dgsw.pioneers.checkIn.global.annotation.AuthCheck;
import dgsw.pioneers.checkIn.global.annotation.WebAdapter;
import dgsw.pioneers.checkIn.global.response.Response;
import dgsw.pioneers.checkIn.global.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@WebAdapter
@RestController
@RequestMapping(value = "/notice")
@RequiredArgsConstructor
@Tag(name = "Notice", description = "Notice Api")
public class NoticeController {

    private final NoticeGenerateUseCase noticeGenerateUseCase;
    private final NoticeDeleteUseCase noticeDeleteUseCase;
    private final NoticeStatusUpdateUseCase noticeStatusUpdateUseCase;
    private final NoticeLoadUseCase noticeLoadUseCase;

    @PostMapping
    @AuthCheck(roles = MemberRole.ADMIN)
    @Operation(summary = "generate Notice", description = "공지 생성", security = @SecurityRequirement(name = "Authorization"))
    public Response generateNotice(
            @RequestBody @Valid NoticeGenerateReq noticeGenerateReq
    ) {
        noticeGenerateUseCase.generateNotice(noticeGenerateReq.mapToDomainEntity());
        return Response.of(HttpStatus.OK, "공지 생성 성공");
    }

    @GetMapping("/all")
    @AuthCheck(roles = MemberRole.ADMIN)
    @Operation(summary = "load all notice", description = "모든 공지 불러오기", security = @SecurityRequirement(name = "Authorization"))
    public ResponseData<List<Notice>> loadAllNotice() {
        List<Notice> notices = noticeLoadUseCase.loadAllNotice();
        return ResponseData.of(HttpStatus.OK, "모든 공지 불러오기 성공", notices);
    }

    @GetMapping("/active")
    @Operation(summary = "load all notice", description = "활성화 상태 공지 불러오기")
    public ResponseData<List<Notice>> loadActiveNotice() {
        List<Notice> notices = noticeLoadUseCase.loadActiveNotice();
        return ResponseData.of(HttpStatus.OK, "활성화 상태 공지 불러오기 성공", notices);
    }

    @DeleteMapping("/{noticeId}")
    @AuthCheck(roles = MemberRole.ADMIN)
    @Operation(summary = "delete notice", description = "공지 삭제", security = @SecurityRequirement(name = "Authorization"))
    public Response deleteNotice(
            @PathVariable("noticeId") long noticeId
    ) {
        noticeDeleteUseCase.deleteNotice(new Notice.NoticeId(noticeId));
        return Response.of(HttpStatus.OK, "공지 삭제 처리 성공");
    }

    @PatchMapping("/status/{noticeId}")
    @AuthCheck(roles = MemberRole.ADMIN)
    @Operation(summary = "modify notice status", description = "공지 상태 수정", security = @SecurityRequirement(name = "Authorization"))
    public Response modifyNoticeStatus(
            @PathVariable("noticeId") long noticeId,
            @RequestBody NoticeStatusModifyReq noticeStatusModifyReq
    ) {
        noticeStatusUpdateUseCase.updateNoticeStatus(new Notice.NoticeId(noticeId), noticeStatusModifyReq.getNoticeStatus());
        return Response.of(HttpStatus.OK, "공지 상태 수정 성공");
    }
}
