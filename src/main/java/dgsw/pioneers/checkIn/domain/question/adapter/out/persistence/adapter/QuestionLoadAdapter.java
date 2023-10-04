package dgsw.pioneers.checkIn.domain.question.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.question.adapter.out.persistence.QuestionMapper;
import dgsw.pioneers.checkIn.domain.question.adapter.out.persistence.QuestionRepository;
import dgsw.pioneers.checkIn.domain.question.application.domain.model.Question;
import dgsw.pioneers.checkIn.domain.question.application.port.out.LoadQuestionPort;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class QuestionLoadAdapter implements LoadQuestionPort {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Override
    public List<Question> loadAllQuestion() {
        return questionRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(questionMapper::mapToDomainEntity).collect(Collectors.toList());
    }
}
