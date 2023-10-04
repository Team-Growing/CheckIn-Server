package dgsw.pioneers.checkIn.domain.question.application.domain.service;

import dgsw.pioneers.checkIn.domain.question.application.domain.model.Question;
import dgsw.pioneers.checkIn.domain.question.application.port.in.QuestionLoadUseCase;
import dgsw.pioneers.checkIn.domain.question.application.port.out.LoadQuestionPort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionLoadService implements QuestionLoadUseCase {

    private final LoadQuestionPort loadQuestionPort;

    @Override
    public List<Question> loadAllQuestion() {
        return loadQuestionPort.loadAllQuestion();
    }
}
