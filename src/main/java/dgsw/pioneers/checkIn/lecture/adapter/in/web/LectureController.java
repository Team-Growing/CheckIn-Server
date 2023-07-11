package dgsw.pioneers.checkIn.lecture.adapter.in.web;

import dgsw.pioneers.checkIn.global.annotation.AuthCheck;
import dgsw.pioneers.checkIn.global.response.Response;
import dgsw.pioneers.checkIn.lecture.adapter.in.web.dto.req.LectureGenerateReq;
import dgsw.pioneers.checkIn.lecture.application.port.in.LectureGenerateUseCase;
import dgsw.pioneers.checkIn.lecture.application.port.in.LectureGenerateCommand;
import dgsw.pioneers.checkIn.global.annotation.WebAdapter;
import dgsw.pioneers.checkIn.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.member.application.domain.model.enums.MemberRole;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping(value = "/lecture")
@RequiredArgsConstructor
@Tag(name = "Lecture", description = "Lecture Api")
public class LectureController {

    private final LectureGenerateUseCase lectureGenerateUseCase;

    @PostMapping
    @AuthCheck(roles = {MemberRole.ADMIN, MemberRole.TEACHER})
    @Operation(summary = "generate Lecture", description = "강좌 생성")
    public Response generateLecture(
            @RequestAttribute Member member,
            @RequestBody LectureGenerateReq request
    ) {

        LectureGenerateCommand lectureGenerateCommand = new LectureGenerateCommand(
                request.getExplanation(),
                request.getPlaceType(),
                request.getAcceptableStudent(),
                request.getLectureSchedule()
        );

        lectureGenerateUseCase.generateLecture(member.getMemberId(), lectureGenerateCommand);
        return Response.of(HttpStatus.OK, "강좌 생성 성공");
    }

    @PatchMapping("/week-plan")
    @AuthCheck(roles = {MemberRole.ADMIN, MemberRole.TEACHER})
    @Operation(summary = "update week plan", description = "주차 게획 수정")
    public Response updateWeekPlan(
            @RequestAttribute Member member,
            @RequestBody LectureGenerateReq request
    ) {

        LectureGenerateCommand lectureGenerateCommand = new LectureGenerateCommand(
                request.getExplanation(),
                request.getPlaceType(),
                request.getAcceptableStudent(),
                request.getLectureSchedule()
        );

        lectureGenerateUseCase.generateLecture(member.getMemberId(), lectureGenerateCommand);
        return Response.of(HttpStatus.OK, "강좌 생성 성공");
    }
}
