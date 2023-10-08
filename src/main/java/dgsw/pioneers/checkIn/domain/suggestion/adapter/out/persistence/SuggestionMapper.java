package dgsw.pioneers.checkIn.domain.suggestion.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.MemberMapper;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.MemberJpaEntity;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.suggestion.adapter.out.persistence.aggregate.SuggestionJpaEntity;
import dgsw.pioneers.checkIn.domain.suggestion.application.domain.model.Suggester;
import dgsw.pioneers.checkIn.domain.suggestion.application.domain.model.Suggestion;
import dgsw.pioneers.checkIn.global.annotation.Mapper;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class SuggestionMapper {

    private final MemberMapper memberMapper;

    public SuggestionJpaEntity mapToJpaEntity(Suggestion suggestion, MemberJpaEntity member) {
        return SuggestionJpaEntity.builder()
                .name(suggestion.getName())
                .content(suggestion.getContent())
                .dayOfWeek(suggestion.getDayOfWeek())
                .additional(getDomainAdditional(suggestion))
                .createdAt(suggestion.getCreatedAt())
                .memberJpaEntity(member)
                .build();
    }

    public Suggestion mapToDomainEntity(SuggestionJpaEntity suggestionJpaEntity) {
        return Suggestion.withId(
                new Suggestion.SuggestionId(suggestionJpaEntity.getId()),
                suggestionJpaEntity.getName(),
                suggestionJpaEntity.getContent(),
                suggestionJpaEntity.getDayOfWeek(),
                getJpaAdditional(suggestionJpaEntity),
                suggestionJpaEntity.getCreatedAt(),
                Suggester.builder()
                        .memberId(new Member.MemberId(suggestionJpaEntity.getMember().getId()))
                        .name(suggestionJpaEntity.getMember().getName())
                        .studentInfo(memberMapper.getStudentInfo(suggestionJpaEntity.getMember().getStudentInfo()))
                        .build()
        );
    }

    private String getDomainAdditional(Suggestion suggestion) {
        return suggestion != null ? suggestion.getAdditional() : null;
    }

    private String getJpaAdditional(SuggestionJpaEntity suggestionJpa) {
        return suggestionJpa != null ? suggestionJpa.getAdditional() : null;
    }
}
