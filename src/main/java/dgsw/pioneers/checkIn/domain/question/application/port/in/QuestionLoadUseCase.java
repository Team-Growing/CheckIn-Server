package dgsw.pioneers.checkIn.domain.question.application.port.in;

import dgsw.pioneers.checkIn.domain.question.application.domain.model.Question;

import java.util.List;

public interface QuestionLoadUseCase {

    Question loadQuestionById(Question.QuestionId questionId);
    List<Question> loadAllQuestion();
}
