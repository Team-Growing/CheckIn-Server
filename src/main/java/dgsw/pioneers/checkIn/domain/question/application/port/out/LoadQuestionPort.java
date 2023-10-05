package dgsw.pioneers.checkIn.domain.question.application.port.out;

import dgsw.pioneers.checkIn.domain.question.application.domain.model.Question;

import java.util.List;

public interface LoadQuestionPort {

    Question loadQuestionById(Question.QuestionId questionId);
    List<Question> loadAllQuestion();
}
