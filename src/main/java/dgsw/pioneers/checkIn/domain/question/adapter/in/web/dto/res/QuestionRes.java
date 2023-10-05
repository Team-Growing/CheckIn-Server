package dgsw.pioneers.checkIn.domain.question.adapter.in.web.dto.res;

import dgsw.pioneers.checkIn.domain.question.application.domain.model.Question;
import dgsw.pioneers.checkIn.domain.question.application.domain.model.enums.QuestionStatus;
import lombok.Getter;

@Getter
public class QuestionRes {

    private final long questionId;
    private final QuestionStatus questionStatus;
    private final String title;
    private final String content;
    private final String name;

    public QuestionRes(Question question) {
        this.questionId = question.getQuestionId().getValue();
        this.questionStatus = question.getQuestionStatus();
        this.title = question.getTitle();
        this.content = question.getContent();
        this.name = question.getQuestioner().getName();
    }
}
