package dgsw.pioneers.checkIn.domain.question.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.adapter.MemberLoadAdapter;
import dgsw.pioneers.checkIn.domain.question.adapter.out.persistence.QuestionMapper;
import dgsw.pioneers.checkIn.domain.question.adapter.out.persistence.QuestionRepository;
import dgsw.pioneers.checkIn.domain.question.adapter.out.persistence.aggregate.QuestionJpaEntity;
import dgsw.pioneers.checkIn.domain.question.application.domain.model.Question;
import dgsw.pioneers.checkIn.domain.question.application.port.out.CreateQuestionPort;
import dgsw.pioneers.checkIn.domain.question.application.port.out.UpdateQuestionStatusPort;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class QuestionPersistenceAdapter implements CreateQuestionPort, UpdateQuestionStatusPort {

    private final QuestionRepository questionRepository;
    private final MemberLoadAdapter memberLoadAdapter;
    private final QuestionMapper questionMapper;

    @Override
    public void createQuestion(Question question) {

        QuestionJpaEntity questionJpaEntity = questionMapper.mapToJpaEntity(
                question,
                memberLoadAdapter.loadMemberJpaEntity(question.getQuestioner().getMemberId().getValue()));

        questionRepository.save(questionJpaEntity);
    }

    @Override
    public void updateQuestionStatus(Question question) {

        QuestionJpaEntity questionJpaEntity = questionRepository.findById(question.getQuestionId().getValue()).get();
        questionJpaEntity.updateQuestionStatus(question.getQuestionStatus());
    }
}
