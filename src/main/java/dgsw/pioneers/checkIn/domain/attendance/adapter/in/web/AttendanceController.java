package dgsw.pioneers.checkIn.domain.attendance.adapter.in.web;

import dgsw.pioneers.checkIn.domain.attendance.adapter.in.web.dto.AttendanceCodeDto;
import dgsw.pioneers.checkIn.domain.attendance.adapter.in.web.dto.req.AttendanceConfirmReq;
import dgsw.pioneers.checkIn.domain.attendance.application.port.in.AttendanceCodeReissueUseCase;
import dgsw.pioneers.checkIn.domain.attendance.application.port.in.AttendanceEradicateUseCase;
import dgsw.pioneers.checkIn.domain.attendance.application.port.in.AttendanceLoadUseCase;
import dgsw.pioneers.checkIn.domain.attendance.application.port.in.AttendanceUseCase;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole;
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

@WebAdapter
@RestController
@RequestMapping(value = "/attendance")
@RequiredArgsConstructor
@Tag(name = "Attendance", description = "Attendance Api")
public class AttendanceController {

    private final AttendanceCodeReissueUseCase attendanceCodeReissueUseCase;
    private final AttendanceLoadUseCase attendanceLoadUseCase;
    private final AttendanceUseCase attendanceUseCase;
    private final AttendanceEradicateUseCase attendanceEradicateUseCase;

    @PatchMapping("/code/{lectureId}")
    @AuthCheck(roles = {MemberRole.TEACHER, MemberRole.ADMIN})
    @Operation(summary = "reissue attendance code", description = "출석 코드 재발급", security = @SecurityRequirement(name = "Authorization"))
    public Response reissueCode(
            @PathVariable("lectureId") long id
    ) {
        attendanceCodeReissueUseCase.reissueCode(new Lecture.LectureId(id));
        return Response.of(HttpStatus.OK, "출석 코드 재발급 성공");
    }

    @GetMapping("/code/{lectureId}")
    @AuthCheck(roles = {MemberRole.TEACHER, MemberRole.ADMIN})
    @Operation(summary = "load attendance code", description = "출석 코드 불러오기", security = @SecurityRequirement(name = "Authorization"))
    public ResponseData<AttendanceCodeDto> getCode(
            @PathVariable("lectureId") long id
    ) {
        AttendanceCodeDto attendanceCodeDto = new AttendanceCodeDto(attendanceLoadUseCase.getAttendanceCode(new Lecture.LectureId(id)));
        return ResponseData.of(HttpStatus.OK, "출석 코드 불러오기 성공", attendanceCodeDto);
    }

    @PostMapping("/{lectureId}")
    @AuthCheck(roles = MemberRole.STUDENT)
    @Operation(summary = "attendance", description = "출석", security = @SecurityRequirement(name = "Authorization"))
    public Response attendance(
            @RequestAttribute Member member,
            @PathVariable("lectureId") long id,
            @RequestBody AttendanceCodeDto attendanceCodeDto
    ) {
        attendanceUseCase.attendanceByCode(new Lecture.LectureId(id), member.getMemberId(), attendanceCodeDto.getCode());
        return Response.of(HttpStatus.OK, "출석 성공");
    }

    @DeleteMapping("/{lectureId}/cancellation/{memberId}")
    @AuthCheck(roles = {MemberRole.TEACHER, MemberRole.ADMIN})
    @Operation(summary = "cancel attendance", description = "출석 취소 처리", security = @SecurityRequirement(name = "Authorization"))
    public Response cancelAttendance(
            @PathVariable("lectureId") long lectureId,
            @PathVariable("memberId") String memberId
    ) {
        attendanceEradicateUseCase.eradicate(new Lecture.LectureId(lectureId), new Member.MemberId(memberId));
        return Response.of(HttpStatus.OK, "출석 취소 처리 성공");
    }

    @PostMapping("/confirmation/{lectureId}")
    @AuthCheck(roles = {MemberRole.TEACHER, MemberRole.ADMIN})
    @Operation(summary = "confirm attendance", description = "출석 확인 처리", security = @SecurityRequirement(name = "Authorization"))
    public Response confirmAttendance(
            @PathVariable("lectureId") long lectureId,
            @RequestBody AttendanceConfirmReq attendanceConfirmReq
    ) {
        attendanceUseCase.attendance(new Lecture.LectureId(lectureId), new Member.MemberId(attendanceConfirmReq.getMemberId()));
        return Response.of(HttpStatus.OK, "출석 확인 처리 성공");
    }
}
