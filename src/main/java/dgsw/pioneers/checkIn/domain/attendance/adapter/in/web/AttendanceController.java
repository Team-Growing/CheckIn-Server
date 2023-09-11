package dgsw.pioneers.checkIn.domain.attendance.adapter.in.web;

import dgsw.pioneers.checkIn.domain.attendance.adapter.in.web.dto.AttendanceReq;
import dgsw.pioneers.checkIn.domain.attendance.application.port.in.AttendanceCodeReissueUseCase;
import dgsw.pioneers.checkIn.domain.attendance.application.port.in.AttendanceUseCase;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
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

@WebAdapter
@RestController
@RequestMapping(value = "/attendance")
@RequiredArgsConstructor
@Tag(name = "Attendance", description = "Attendance Api")
public class AttendanceController {

    private final AttendanceCodeReissueUseCase attendanceCodeReissueUseCase;
    private final AttendanceUseCase attendanceUseCase;

    @PatchMapping("/code/{lectureId}")
    @AuthCheck(roles = {MemberRole.TEACHER, MemberRole.ADMIN})
    @Operation(summary = "Reissue Attendance Code", description = "출석 코드 재발급", security = @SecurityRequirement(name = "Authorization"))
    public Response reissueCode(
            @PathVariable("lectureId") long id
    ) {
        attendanceCodeReissueUseCase.reissueCode(new Lecture.LectureId(id));
        return Response.of(HttpStatus.OK, "출석 코드 재발급 성공");
    }

    @PostMapping("/{lectureId}")
    @AuthCheck(roles = MemberRole.STUDENT)
    @Operation(summary = "Attendance", description = "출석", security = @SecurityRequirement(name = "Authorization"))
    public Response attendance(
            @RequestAttribute Member member,
            @PathVariable("lectureId") long id,
            @RequestBody AttendanceReq attendanceReq
    ) {
        attendanceUseCase.attendance(new Lecture.LectureId(id), member.getMemberId(), attendanceReq.getCode());
        return Response.of(HttpStatus.OK, "출석 성공");
    }
}