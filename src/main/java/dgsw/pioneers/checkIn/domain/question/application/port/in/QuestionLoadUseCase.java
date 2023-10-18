package dgsw.pioneers.checkIn.domain.question.application.port.in;

import dgsw.pioneers.checkIn.domain.question.adapter.in.web.dto.res.QuestionWithTotalCountRes;
import dgsw.pioneers.checkIn.domain.question.application.domain.model.Question;

public interface QuestionLoadUseCase {

    Question loadQuestionById(Question.QuestionId questionId);
    QuestionWithTotalCountRes loadAllQuestion(int page, int limit);
}
