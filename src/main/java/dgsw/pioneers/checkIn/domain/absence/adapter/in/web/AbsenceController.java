package dgsw.pioneers.checkIn.domain.absence.adapter.in.web;

import dgsw.pioneers.checkIn.domain.absence.adapter.in.web.dto.req.AbsenceGenerateReq;
import dgsw.pioneers.checkIn.domain.absence.application.domain.model.Absence;
import dgsw.pioneers.checkIn.domain.absence.application.port.in.AbsenceStatusUpdateUseCase;
import dgsw.pioneers.checkIn.domain.absence.application.port.in.AbsenceUseCase;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole;
import dgsw.pioneers.checkIn.global.annotation.AuthCheck;
import dgsw.pioneers.checkIn.global.annotation.WebAdapter;
import dgsw.pioneers.checkIn.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@WebAdapter
@RestController
@RequestMapping(value = "/absence")
@RequiredArgsConstructor
@Tag(name = "Absence", description = "Absence Api")
public class AbsenceController {

    private final AbsenceUseCase absenceUseCase;
    private final AbsenceStatusUpdateUseCase absenceStatusUpdateUseCase;

    @PatchMapping("/allow/{absenceId}")
    @AuthCheck(roles = MemberRole.ADMIN)
    @Operation(summary = "allow absence", description = "결강 승인", security = @SecurityRequirement(name = "Authorization"))
    public Response allowAbsence(
            @PathVariable("absenceId") long id
    ) {
        absenceStatusUpdateUseCase.allow(new Absence.AbsenceId(id));
        return Response.of(HttpStatus.OK, "결강 승인 성공");
    }

    @PatchMapping("/deny/{absenceId}")
    @AuthCheck(roles = MemberRole.ADMIN)
    @Operation(summary = "deny absence", description = "결강 거절 재발급", security = @SecurityRequirement(name = "Authorization"))
    public Response denyAbsence(
            @PathVariable("absenceId") long id
    ) {
        absenceStatusUpdateUseCase.deny(new Absence.AbsenceId(id));
        return Response.of(HttpStatus.OK, "결강 거절 성공");
    }

    @PostMapping
    @AuthCheck(roles = MemberRole.STUDENT)
    @Operation(summary = "absence", description = "결강 신청", security = @SecurityRequirement(name = "Authorization"))
    public Response absence(
            @RequestAttribute Member member,
            @RequestBody @Valid AbsenceGenerateReq absenceGenerateReq
    ) {
        absenceUseCase.absence(absenceGenerateReq, member.getMemberId());
        return Response.of(HttpStatus.OK, "결강 신청 성공");
    }
}
