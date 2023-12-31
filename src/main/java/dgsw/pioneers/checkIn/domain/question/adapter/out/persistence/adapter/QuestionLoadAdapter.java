package dgsw.pioneers.checkIn.domain.question.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.question.adapter.out.persistence.QuestionMapper;
import dgsw.pioneers.checkIn.domain.question.adapter.out.persistence.QuestionRepository;
import dgsw.pioneers.checkIn.domain.question.adapter.out.persistence.aggregate.QuestionJpaEntity;
import dgsw.pioneers.checkIn.domain.question.application.domain.model.Question;
import dgsw.pioneers.checkIn.domain.question.application.port.out.LoadQuestionPort;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import dgsw.pioneers.checkIn.global.exception.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class QuestionLoadAdapter implements LoadQuestionPort {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Override
    public Question loadQuestionById(Question.QuestionId questionId) {

        QuestionJpaEntity questionJpaEntity = questionRepository.findById(questionId.getValue())
                .orElseThrow(ResourceNotFoundException::new);

        return questionMapper.mapToDomainEntity(questionJpaEntity);
    }

    @Override
    public List<Question> loadAllQuestion(PageRequest pageRequest) {
        return questionRepository.findAllByOrderByCreatedAtDesc(pageRequest).stream()
                .map(questionMapper::mapToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public Long loadAllCount() {
        return questionRepository.countBy();
    }
}
