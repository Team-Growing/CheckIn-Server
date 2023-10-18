package dgsw.pioneers.checkIn.domain.suggestion.adapter.in.web;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole;
import dgsw.pioneers.checkIn.domain.suggestion.adapter.in.web.dto.req.SuggestionGenerateReq;
import dgsw.pioneers.checkIn.domain.suggestion.adapter.in.web.dto.res.SuggestionWithTotalCountRes;
import dgsw.pioneers.checkIn.domain.suggestion.application.domain.model.Suggestion;
import dgsw.pioneers.checkIn.domain.suggestion.application.port.in.SuggestionGenerateUseCase;
import dgsw.pioneers.checkIn.domain.suggestion.application.port.in.SuggestionLoadUseCase;
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
@RequestMapping(value = "/suggestion")
@RequiredArgsConstructor
@Tag(name = "Suggestion", description = "Suggestion Api")
public class SuggestionController {

    private final SuggestionGenerateUseCase suggestionGenerateUseCase;
    private final SuggestionLoadUseCase suggestionLoadUseCase;

    @PostMapping
    @AuthCheck(roles = MemberRole.STUDENT)
    @Operation(summary = "generate suggestion", description = "수업 제안 생성", security = @SecurityRequirement(name = "Authorization"))
    public Response generateNotice(
            @RequestAttribute Member member,
            @RequestBody @Valid SuggestionGenerateReq suggestionGenerateReq
    ) {
        suggestionGenerateUseCase.generateSuggestion(suggestionGenerateReq.mapToDomainEntity(member.getMemberId()));
        return Response.of(HttpStatus.OK, "수업 제안 생성 성공");
    }

    @GetMapping
    @AuthCheck(roles = MemberRole.ADMIN)
    @Operation(summary = "load all suggestion", description = "모든 제안 불러오기", security = @SecurityRequirement(name = "Authorization"))
    public ResponseData<SuggestionWithTotalCountRes> loadNotice(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "limit") int limit
    ) {
        SuggestionWithTotalCountRes res = suggestionLoadUseCase.loadSuggestion(page, limit);
        return ResponseData.of(HttpStatus.OK, "모든 제안 불러오기 성공", res);
    }

    @GetMapping("/{suggestionId}")
    @AuthCheck(roles = MemberRole.ADMIN)
    @Operation(summary = "load suggestion", description = "단일 제안 불러오기", security = @SecurityRequirement(name = "Authorization"))
    public ResponseData<Suggestion> loadSuggestion(
            @PathVariable("suggestionId") long id
    ) {
        Suggestion suggestion = suggestionLoadUseCase.loadSuggestionById(new Suggestion.SuggestionId(id));
        return ResponseData.of(HttpStatus.OK, "단일 제안 불러오기 성공", suggestion);
    }
}
