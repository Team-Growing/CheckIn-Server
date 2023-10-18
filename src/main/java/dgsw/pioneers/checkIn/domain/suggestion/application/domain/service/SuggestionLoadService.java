package dgsw.pioneers.checkIn.domain.suggestion.application.domain.service;

import dgsw.pioneers.checkIn.domain.suggestion.adapter.in.web.dto.res.SuggestionWithTotalCountRes;
import dgsw.pioneers.checkIn.domain.suggestion.application.domain.model.Suggestion;
import dgsw.pioneers.checkIn.domain.suggestion.application.port.in.SuggestionLoadUseCase;
import dgsw.pioneers.checkIn.domain.suggestion.application.port.out.LoadSuggestionPort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SuggestionLoadService implements SuggestionLoadUseCase {

    private final LoadSuggestionPort loadSuggestionPort;

    @Override
    public SuggestionWithTotalCountRes loadSuggestion(int page, int limit) {

        PageRequest pageRequest = PageRequest.of(page - 1, limit);

        return SuggestionWithTotalCountRes.builder()
                .totalCount(loadSuggestionPort.loadAllCount())
                .value(loadSuggestionPort.loadAllSuggestion(pageRequest))
                .build();
    }

    @Override
    public Suggestion loadSuggestionById(Suggestion.SuggestionId suggestionId) {
        return loadSuggestionPort.loadSuggestionById(suggestionId);
    }
}
