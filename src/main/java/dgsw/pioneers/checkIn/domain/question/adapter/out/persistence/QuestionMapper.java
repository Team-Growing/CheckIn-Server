package dgsw.pioneers.checkIn.domain.question.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.MemberJpaEntity;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.question.adapter.out.persistence.aggregate.QuestionJpaEntity;
import dgsw.pioneers.checkIn.domain.question.application.domain.model.Question;
import dgsw.pioneers.checkIn.domain.question.application.domain.model.Questioner;
import dgsw.pioneers.checkIn.global.annotation.Mapper;

@Mapper
public class QuestionMapper {

    public QuestionJpaEntity mapToJpaEntity(Question question, MemberJpaEntity member) {
        return QuestionJpaEntity.builder()
                .title(question.getTitle())
                .content(question.getContent())
                .questionStatus(question.getQuestionStatus())
                .createdAt(question.getCreatedAt())
                .memberJpaEntity(member)
                .build();
    }

    public Question mapToDomainEntity(QuestionJpaEntity questionJpaEntity) {
        return Question.withId(
                new Question.QuestionId(questionJpaEntity.getId()),
                questionJpaEntity.getQuestionStatus(),
                questionJpaEntity.getTitle(),
                questionJpaEntity.getContent(),
                questionJpaEntity.getCreatedAt(),
                Questioner.builder()
                        .memberId(new Member.MemberId(questionJpaEntity.getMember().getId()))
                        .name(questionJpaEntity.getMember().getName())
                        .build()
        );
    }
}
