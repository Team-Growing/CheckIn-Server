package dgsw.pioneers.checkIn.domain.question.application.port.out;

import dgsw.pioneers.checkIn.domain.question.application.domain.model.Question;

public interface UpdateQuestionStatusPort {

    void updateQuestionStatus(Question question);
}
