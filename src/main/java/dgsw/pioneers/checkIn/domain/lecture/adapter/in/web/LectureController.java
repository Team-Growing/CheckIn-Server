package dgsw.pioneers.checkIn.domain.lecture.adapter.in.web;

import dgsw.pioneers.checkIn.domain.lecture.adapter.in.web.dto.req.LectureGenerateReq;
import dgsw.pioneers.checkIn.domain.lecture.adapter.in.web.dto.req.LectureStatusUpdateReq;
import dgsw.pioneers.checkIn.domain.lecture.adapter.in.web.dto.res.LectureWithMyInfoRes;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture.LectureId;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.domain.lecture.application.port.in.LectureGenerateUseCase;
import dgsw.pioneers.checkIn.domain.lecture.application.port.in.LectureLoadUseCase;
import dgsw.pioneers.checkIn.domain.lecture.application.port.in.LectureStatusUpdateUseCase;
import dgsw.pioneers.checkIn.domain.lecture.application.port.in.WeekPlanUpdateUseCase;
import dgsw.pioneers.checkIn.domain.lecture.application.port.in.MemberLectureLoadUseCase;
import dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.res.MemberInfoRes;
import dgsw.pioneers.checkIn.global.annotation.AuthCheck;
import dgsw.pioneers.checkIn.global.response.Response;
import dgsw.pioneers.checkIn.domain.lecture.adapter.in.web.dto.req.WeekPlanUpdateReq;
import dgsw.pioneers.checkIn.global.annotation.WebAdapter;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole;
import dgsw.pioneers.checkIn.global.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@WebAdapter
@RestController
@RequestMapping(value = "/lecture")
@RequiredArgsConstructor
@Tag(name = "Lecture", description = "Lecture Api")
public class LectureController {

    private final LectureGenerateUseCase lectureGenerateUseCase;
    private final LectureStatusUpdateUseCase lectureStatusUpdateUseCase;
    private final WeekPlanUpdateUseCase weekPlanUpdateUseCase;
    private final LectureLoadUseCase lectureLoadUseCase;
    private final MemberLectureLoadUseCase memberLectureLoadUseCase;

    @PostMapping
    @AuthCheck(roles = MemberRole.ADMIN)
    @Operation(summary = "generate lecture", description = "강좌 생성", security = @SecurityRequirement(name = "Authorization"))
    public Response generateLecture(
            @RequestBody @Valid LectureGenerateReq lectureGenerateReq
    ) {
        lectureGenerateUseCase.generateLecture(lectureGenerateReq.mapToDomainEntity());
        return Response.of(HttpStatus.OK, "강좌 생성 성공");
    }

    @PatchMapping("/week-plan/{lectureId}")
    @AuthCheck(roles = {MemberRole.ADMIN, MemberRole.TEACHER})
    @Operation(summary = "update week plan", description = "주차 게획 수정", security = @SecurityRequirement(name = "Authorization"))
    public Response updateWeekPlan(
            @RequestAttribute Member member,
            @PathVariable("lectureId") long id,
            @RequestBody @Valid WeekPlanUpdateReq weekPlanUpdateReq
    ) {
        weekPlanUpdateUseCase.updateWeekPlan(member, new Lecture.LectureId(id), weekPlanUpdateReq.getWeekPlans());
        return Response.of(HttpStatus.OK, "주차 게획 수정 성공");
    }

    @GetMapping("/{lectureId}")
    @Operation(summary = "load lecture", description = "강좌 불러오기")
    public ResponseData<Lecture> loadLecture(
            @PathVariable("lectureId") long id
    ) {
        Lecture lecture = lectureLoadUseCase.loadLecture(new Lecture.LectureId(id));
        return ResponseData.of(HttpStatus.OK, "강좌 불러오기 성공", lecture);
    }

    @GetMapping("/today")
    @Operation(summary = "load today lecture", description = "오늘 강좌 불러오기")
    public ResponseData<List<Lecture>> loadTodayLecture() {
        List<Lecture> lectures = lectureLoadUseCase.loadTodayLecture();
        return ResponseData.of(HttpStatus.OK, "오늘 강좌 불러오기 성공", lectures);
    }

    @GetMapping
    @AuthCheck
    @Operation(summary = "load lectures with lectureStatus", description = "강좌 상태로 강좌 불러오기", security = @SecurityRequirement(name = "Authorization"))
    public ResponseData<List<Lecture>> loadLectureByStatus(
            @RequestAttribute Member member,
            @RequestParam("status") LectureStatus lectureStatus,
            @RequestParam("grade") @Min(1) @Max(2) int targetGrade
    ) {
        List<Lecture> lectures = lectureLoadUseCase.loadAllLectureByStatusAndTargetGrade(lectureStatus, member.getMemberRole(), targetGrade);
        return ResponseData.of(HttpStatus.OK, "강좌 상태로 강좌 불러오기 성공", lectures);
    }

    @GetMapping("/all")
    @AuthCheck(roles = MemberRole.ADMIN)
    @Operation(summary = "load all lectures", description = "모든 강좌 불러오기", security = @SecurityRequirement(name = "Authorization"))
    public ResponseData<List<Lecture>> loadAllLecture(
            @RequestParam("grade") @Min(1) @Max(2) int targetGrade
    ) {
        List<Lecture> res = lectureLoadUseCase.loadAllLecture(targetGrade);
        return ResponseData.of(HttpStatus.OK, "모든 강좌 불러오기 성공", res);
    }

    @PatchMapping("/status")
    @AuthCheck(roles = MemberRole.ADMIN)
    @Operation(summary = "update lectureStatus", description = "강좌 상태 수정", security = @SecurityRequirement(name = "Authorization"))
    public Response updateLectureStatus(
            @RequestBody @Valid LectureStatusUpdateReq lectureStatusUpdateReq
    ) {
        lectureStatusUpdateUseCase.updateStatus(
                lectureStatusUpdateReq.getIds().stream().map(LectureId::new).collect(Collectors.toList()),
                lectureStatusUpdateReq.getLectureStatus()
        );
        return Response.of(HttpStatus.OK, "강좌 상태 수정 성공");
    }

    @AuthCheck(roles = {MemberRole.STUDENT, MemberRole.TEACHER})
    @Operation(summary = "load member lectures", description = "내 강좌 정보 불러오기", security = @SecurityRequirement(name = "Authorization"))
    @GetMapping("/my")
    public ResponseData<LectureWithMyInfoRes> getMemberLecture(
            @RequestAttribute Member member
    ) {
        List<Lecture> lectures = memberLectureLoadUseCase.loadLectureByMember(member);
        LectureWithMyInfoRes lectureWithMyInfoRes = LectureWithMyInfoRes.builder()
                .info(MemberInfoRes.convertToDTO(member))
                .lectures(lectures).build();
        return ResponseData.of(HttpStatus.OK, "내 강좌 정보 불러오기 성공", lectureWithMyInfoRes);
    }

    @AuthCheck(roles = {MemberRole.STUDENT, MemberRole.TEACHER})
    @Operation(summary = "load today member lectures", description = "오늘 내 강좌 정보 불러오기", security = @SecurityRequirement(name = "Authorization"))
    @GetMapping("/my/today")
    public ResponseData<List<Lecture>> getTodayMemberLecture(
            @RequestAttribute Member member
    ) {
        List<Lecture> lectures = memberLectureLoadUseCase.loadTodayLectureByMember(member);
        return ResponseData.of(HttpStatus.OK, "오늘 내 강좌 정보 불러오기 성공", lectures);
    }
}
