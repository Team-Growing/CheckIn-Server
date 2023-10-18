package dgsw.pioneers.checkIn.domain.question.application.domain.service;

import dgsw.pioneers.checkIn.domain.question.adapter.in.web.dto.res.QuestionWithTotalCountRes;
import dgsw.pioneers.checkIn.domain.question.application.domain.model.Question;
import dgsw.pioneers.checkIn.domain.question.application.domain.model.enums.QuestionStatus;
import dgsw.pioneers.checkIn.domain.question.application.port.in.QuestionLoadUseCase;
import dgsw.pioneers.checkIn.domain.question.application.port.out.LoadQuestionPort;
import dgsw.pioneers.checkIn.domain.question.application.port.out.UpdateQuestionStatusPort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestionLoadService implements QuestionLoadUseCase {

    private final LoadQuestionPort loadQuestionPort;
    private final UpdateQuestionStatusPort updateQuestionStatusPort;

    @Override
    @Transactional
    public Question loadQuestionById(Question.QuestionId questionId) {

        Question question = loadQuestionPort.loadQuestionById(questionId);

        if (question.getQuestionStatus().equals(QuestionStatus.CONFIRMED)) {
            return question;
        }

        question.confirmQuestion();
        updateQuestionStatusPort.updateQuestionStatus(question);

        return question;
    }

    @Override
    public QuestionWithTotalCountRes loadAllQuestion(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page - 1, limit);

        return QuestionWithTotalCountRes.builder()
                .totalCount(loadQuestionPort.loadAllCount())
                .value(loadQuestionPort.loadAllQuestion(pageRequest))
                .build();
    }
}
