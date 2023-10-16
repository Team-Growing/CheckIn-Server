package dgsw.pioneers.checkIn.domain.member.adapter.in.web;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.req.UpdateStudentInfoReq;
import dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.req.UpdateTeacherInfoReq;
import dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.res.MemberInfoRes;
import dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.res.TeacherInfoRes;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole;
import dgsw.pioneers.checkIn.domain.member.application.port.in.MemberInfoUpdateUseCase;
import dgsw.pioneers.checkIn.domain.member.application.port.in.MemberLectureLoadUseCase;
import dgsw.pioneers.checkIn.domain.member.application.port.in.MemberLoadUseCase;
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
@RequestMapping(value = "/member")
@RequiredArgsConstructor
@Tag(name = "Member", description = "Member Api")
public class MemberController {

    private final MemberLectureLoadUseCase memberLectureLoadUseCase;
    private final MemberLoadUseCase memberLoadUseCase;
    private final MemberInfoUpdateUseCase memberInfoUpdateUseCase;

    @AuthCheck
    @Operation(summary = "load memberInfo", description = "내 정보 불러오기", security = @SecurityRequirement(name = "Authorization"))
    @GetMapping("/my")
    public ResponseData<MemberInfoRes> getMemberInfo(@RequestAttribute Member member) {
        MemberInfoRes memberInfoRes = MemberInfoRes.convertToDTO(member);
        return ResponseData.of(HttpStatus.OK, "내 정보 불러오기 성공", memberInfoRes);
    }

    @PatchMapping("/info/student")
    @AuthCheck(roles = MemberRole.STUDENT)
    @Operation(summary = "update student info", description = "학생 정보 수정", security = @SecurityRequirement(name = "Authorization"))
    public Response updateStudentInfo(
            @RequestAttribute Member member,
            @RequestBody @Valid UpdateStudentInfoReq updateStudentInfoReq
    ) {
        memberInfoUpdateUseCase.updateStudentInfo(member, updateStudentInfoReq);
        return Response.of(HttpStatus.OK, "학생 정보 수정 성공");
    }

    @PatchMapping("/info/teacher")
    @AuthCheck(roles = MemberRole.TEACHER)
    @Operation(summary = "update teacher info", description = "선생님 정보 수정", security = @SecurityRequirement(name = "Authorization"))
    public Response updateTeacherInfo(
            @RequestAttribute Member member,
            @RequestBody @Valid UpdateTeacherInfoReq updateTeacherInfoReq
            ) {
        memberInfoUpdateUseCase.updateTeacherInfo(member, updateTeacherInfoReq);
        return Response.of(HttpStatus.OK, "선생님 정보 수정 성공");
    }

    @AuthCheck
    @Operation(summary = "load member lectures", description = "내 강좌 정보 불러오기", security = @SecurityRequirement(name = "Authorization"))
    @GetMapping("/lectures/my")
    public ResponseData<List<Lecture>> getMemberLecture(@RequestAttribute Member member) {
        List<Lecture> lectures = memberLectureLoadUseCase.loadLectureByMember(member.getMemberId());
        return ResponseData.of(HttpStatus.OK, "내 강좌 정보 불러오기 성공", lectures);
    }

    @AuthCheck
    @Operation(summary = "load today member lectures", description = "오늘 내 강좌 정보 불러오기", security = @SecurityRequirement(name = "Authorization"))
    @GetMapping("/lectures/my/today")
    public ResponseData<List<Lecture>> getTodayMemberLecture(@RequestAttribute Member member) {
        List<Lecture> lectures = memberLectureLoadUseCase.loadTodayLectureByMember(member.getMemberId());
        return ResponseData.of(HttpStatus.OK, "오늘 내 강좌 정보 불러오기 성공", lectures);
    }

    @AuthCheck(roles = MemberRole.ADMIN)
    @Operation(summary = "load teachers info", description = "선생님 리스트 불러오기", security = @SecurityRequirement(name = "Authorization"))
    @GetMapping("/teachers")
    public ResponseData<List<TeacherInfoRes>> getTeachers() {
        List<TeacherInfoRes> teacherInfoRes = memberLoadUseCase.loadTeachers().stream().map(TeacherInfoRes::convertToDTO).toList();
        return ResponseData.of(HttpStatus.OK, "선생님 리스트 불러오기 성공", teacherInfoRes);
    }
}
