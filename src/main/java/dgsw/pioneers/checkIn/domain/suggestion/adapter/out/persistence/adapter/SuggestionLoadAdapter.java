package dgsw.pioneers.checkIn.domain.suggestion.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.suggestion.adapter.out.persistence.SuggestionMapper;
import dgsw.pioneers.checkIn.domain.suggestion.adapter.out.persistence.SuggestionRepository;
import dgsw.pioneers.checkIn.domain.suggestion.application.domain.model.Suggestion;
import dgsw.pioneers.checkIn.domain.suggestion.application.port.out.LoadSuggestionPort;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class SuggestionLoadAdapter implements LoadSuggestionPort {

    private final SuggestionRepository suggestionRepository;
    private final SuggestionMapper suggestionMapper;

    @Override
    public List<Suggestion> loadAllSuggestion() {
        return suggestionRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(suggestionMapper::mapToDomainEntity).collect(Collectors.toList());
    }
}
