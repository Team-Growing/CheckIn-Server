package dgsw.pioneers.checkIn.domain.question.application.port.in;

import dgsw.pioneers.checkIn.domain.question.application.domain.model.Question;

public interface QuestionGenerateUseCase {

    void generateQuestion(Question question);
}
