package dgsw.pioneers.checkIn.domain.lecture.adapter.in.web;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.port.in.EnrolmentUseCase;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole;
import dgsw.pioneers.checkIn.global.annotation.AuthCheck;
import dgsw.pioneers.checkIn.global.annotation.WebAdapter;
import dgsw.pioneers.checkIn.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@RestController
@RequestMapping(value = "/enrol")
@RequiredArgsConstructor
@Tag(name = "Enrol", description = "Enrol Api")
public class EnrolController {

    private final EnrolmentUseCase enrolmentUseCase;

    @PostMapping("/{id}")
    @AuthCheck(roles = {MemberRole.STUDENT})
    @Operation(summary = "Enrolment Lecture", description = "강좌 수강 신청")
    public Response generateLecture(
            @RequestAttribute Member member,
            @PathVariable long id
    ) {
        enrolmentUseCase.lectureEnrolment(new Lecture.LectureId(id), member);
        return Response.of(HttpStatus.OK, "강좌 수강 신청 성공");
    }
}
