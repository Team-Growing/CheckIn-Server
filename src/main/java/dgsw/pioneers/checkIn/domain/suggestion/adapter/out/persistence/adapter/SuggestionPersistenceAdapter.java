package dgsw.pioneers.checkIn.domain.suggestion.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.adapter.MemberLoadAdapter;
import dgsw.pioneers.checkIn.domain.suggestion.adapter.out.persistence.SuggestionMapper;
import dgsw.pioneers.checkIn.domain.suggestion.adapter.out.persistence.SuggestionRepository;
import dgsw.pioneers.checkIn.domain.suggestion.adapter.out.persistence.aggregate.SuggestionJpaEntity;
import dgsw.pioneers.checkIn.domain.suggestion.application.domain.model.Suggestion;
import dgsw.pioneers.checkIn.domain.suggestion.application.port.out.CreateSuggestionPort;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class SuggestionPersistenceAdapter implements CreateSuggestionPort {

    private final SuggestionRepository suggestionRepository;
    private final MemberLoadAdapter memberLoadAdapter;
    private final SuggestionMapper suggestionMapper;

    @Override
    public void createSuggestion(Suggestion suggestion) {

        SuggestionJpaEntity suggestionJpaEntity = suggestionMapper.mapToJpaEntity(
                suggestion,
                memberLoadAdapter.loadMemberJpaEntity(suggestion.getSuggester().getMemberId().getValue()));

        suggestionRepository.save(suggestionJpaEntity);
    }
}
