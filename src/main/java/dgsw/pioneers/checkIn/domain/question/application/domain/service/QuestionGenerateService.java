package dgsw.pioneers.checkIn.domain.question.application.domain.service;

import dgsw.pioneers.checkIn.domain.question.application.domain.model.Question;
import dgsw.pioneers.checkIn.domain.question.application.port.in.QuestionGenerateUseCase;
import dgsw.pioneers.checkIn.domain.question.application.port.out.CreateQuestionPort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionGenerateService implements QuestionGenerateUseCase {

    private final CreateQuestionPort createQuestionPort;

    @Override
    @Transactional
    public void generateQuestion(Question question) {

        createQuestionPort.createQuestion(question);
    }
}
