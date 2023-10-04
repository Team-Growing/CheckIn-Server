package dgsw.pioneers.checkIn.domain.notice.adapter.in.web;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole;
import dgsw.pioneers.checkIn.domain.notice.adapter.in.web.dto.req.NoticeGenerateReq;
import dgsw.pioneers.checkIn.domain.notice.application.port.in.NoticeGenerateUseCase;
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

@WebAdapter
@RestController
@RequestMapping(value = "/notice")
@RequiredArgsConstructor
@Tag(name = "Notice", description = "Notice Api")
public class NoticeController {

    private final NoticeGenerateUseCase noticeGenerateUseCase;

    @PostMapping
    @AuthCheck(roles = MemberRole.ADMIN)
    @Operation(summary = "generate Notice", description = "공지 생성", security = @SecurityRequirement(name = "Authorization"))
    public Response generateNotice(
            @RequestBody @Valid NoticeGenerateReq noticeGenerateReq
    ) {
        noticeGenerateUseCase.generateNotice(noticeGenerateReq.mapToDomainEntity());
        return Response.of(HttpStatus.OK, "공지 생성 성공");
    }
}
