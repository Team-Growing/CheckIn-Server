package dgsw.pioneers.checkIn.domain.question.adapter.in.web;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole;
import dgsw.pioneers.checkIn.domain.question.adapter.in.web.dto.req.QuestionGenerateReq;
import dgsw.pioneers.checkIn.domain.question.application.domain.model.Question;
import dgsw.pioneers.checkIn.domain.question.application.port.in.QuestionGenerateUseCase;
import dgsw.pioneers.checkIn.domain.question.application.port.in.QuestionLoadUseCase;
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
@RequestMapping(value = "/question")
@RequiredArgsConstructor
@Tag(name = "Question", description = "Question Api")
public class QuestionController {

    private final QuestionGenerateUseCase questionGenerateUseCase;
    private final QuestionLoadUseCase questionLoadUseCase;

    @PostMapping
    @AuthCheck(roles = MemberRole.TEACHER)
    @Operation(summary = "generate question", description = "문의 생성", security = @SecurityRequirement(name = "Authorization"))
    public Response generateQuestion(
            @RequestAttribute Member member,
            @RequestBody @Valid QuestionGenerateReq questionGenerateReq
    ) {
        questionGenerateUseCase.generateQuestion(questionGenerateReq.mapToDomainEntity(member.getMemberId()));
        return Response.of(HttpStatus.OK, "문의 생성 성공");
    }

    @GetMapping("/all")
    @AuthCheck(roles = MemberRole.ADMIN)
    @Operation(summary = "load all question", description = "모든 문의 불러오기", security = @SecurityRequirement(name = "Authorization"))
    public ResponseData<List<Question>> loadAllQuestion() {
        List<Question> questions = questionLoadUseCase.loadAllQuestion();
        return ResponseData.of(HttpStatus.OK, "모든 문의 불러오기 성공", questions);
    }
}
