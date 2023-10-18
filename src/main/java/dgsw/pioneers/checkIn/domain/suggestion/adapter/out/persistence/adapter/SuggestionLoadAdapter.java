package dgsw.pioneers.checkIn.domain.suggestion.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.suggestion.adapter.out.persistence.SuggestionMapper;
import dgsw.pioneers.checkIn.domain.suggestion.adapter.out.persistence.SuggestionRepository;
import dgsw.pioneers.checkIn.domain.suggestion.adapter.out.persistence.aggregate.SuggestionJpaEntity;
import dgsw.pioneers.checkIn.domain.suggestion.application.domain.model.Suggestion;
import dgsw.pioneers.checkIn.domain.suggestion.application.port.out.LoadSuggestionPort;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import dgsw.pioneers.checkIn.global.exception.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class SuggestionLoadAdapter implements LoadSuggestionPort {

    private final SuggestionRepository suggestionRepository;
    private final SuggestionMapper suggestionMapper;

    @Override
    public Suggestion loadSuggestionById(Suggestion.SuggestionId suggestionId) {

        SuggestionJpaEntity suggestionJpaEntity = suggestionRepository.findById(suggestionId.getValue())
                .orElseThrow(ResourceNotFoundException::new);

        return suggestionMapper.mapToDomainEntity(suggestionJpaEntity);
    }

    @Override
    public List<Suggestion> loadAllSuggestion(PageRequest pageRequest) {
        return suggestionRepository.findAllByOrderByCreatedAtDesc(pageRequest).stream()
                .map(suggestionMapper::mapToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public Long loadAllCount() {
        return suggestionRepository.countBy();
    }
}
