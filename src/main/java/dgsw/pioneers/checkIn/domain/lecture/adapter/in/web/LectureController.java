package dgsw.pioneers.checkIn.domain.lecture.adapter.in.web;

import dgsw.pioneers.checkIn.domain.lecture.adapter.in.web.dto.req.LectureGenerateReq;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.port.in.LectureGenerateUseCase;
import dgsw.pioneers.checkIn.domain.lecture.application.port.in.LectureLoadUseCase;
import dgsw.pioneers.checkIn.domain.lecture.application.port.in.WeekPlanUpdateUseCase;
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
import java.util.List;

@WebAdapter
@RestController
@RequestMapping(value = "/lecture")
@RequiredArgsConstructor
@Tag(name = "Lecture", description = "Lecture Api")
public class LectureController {

    private final LectureGenerateUseCase lectureGenerateUseCase;
    private final WeekPlanUpdateUseCase weekPlanUpdateUseCase;
    private final LectureLoadUseCase lectureLoadUseCase;

    @PostMapping
    @AuthCheck(roles = MemberRole.ADMIN)
    @Operation(summary = "generate Lecture", description = "강좌 생성", security = @SecurityRequirement(name = "Authorization"))
    public Response generateLecture(
            @RequestAttribute Member member,
            @RequestBody @Valid LectureGenerateReq lectureGenerateReq
    ) {
        lectureGenerateUseCase.generateLecture(lectureGenerateReq.mapToDomainEntity(member.getMemberId()));
        return Response.of(HttpStatus.OK, "강좌 생성 성공");
    }

    @PatchMapping("/week-plan")
    @AuthCheck(roles = {MemberRole.ADMIN, MemberRole.TEACHER})
    @Operation(summary = "update week plan", description = "주차 게획 수정", security = @SecurityRequirement(name = "Authorization"))
    public Response updateWeekPlan(
            @RequestAttribute Member member,
            @RequestBody @Valid WeekPlanUpdateReq weekPlanUpdateReq
    ) {
        weekPlanUpdateUseCase.updateWeekPlan(member.getMemberId(), weekPlanUpdateReq, member.getMemberRole());
        return Response.of(HttpStatus.OK, "주차 게획 수정 성공");
    }

    @GetMapping("/{id}")
    @Operation(summary = "load lecture", description = "강좌 불러오기")
    public ResponseData<Lecture> loadLecture(@PathVariable long id) {
        Lecture lecture = lectureLoadUseCase.loadLecture(new Lecture.LectureId(id));
        return ResponseData.of(HttpStatus.OK, "강좌 불러오기 성공", lecture);
    }

    @GetMapping("/today")
    @Operation(summary = "load today lecture", description = "오늘 강좌 불러오기")
    public ResponseData<List<Lecture>> loadTodayLecture() {
        List<Lecture> lectures = lectureLoadUseCase.loadTodayLecture();
        return ResponseData.of(HttpStatus.OK, "오늘 강좌 불러오기 성공", lectures);
    }
}
