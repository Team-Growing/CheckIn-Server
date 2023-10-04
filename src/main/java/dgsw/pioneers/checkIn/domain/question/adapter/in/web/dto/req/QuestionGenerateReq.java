package dgsw.pioneers.checkIn.domain.question.adapter.in.web.dto.req;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.question.application.domain.model.Question;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class QuestionGenerateReq {

    @NotBlank
    private String title;
    @NotBlank
    private String content;

    public Question mapToDomainEntity(Member.MemberId teacherId) {
        return Question.generate(this.title, this.content, teacherId);
    }
}
