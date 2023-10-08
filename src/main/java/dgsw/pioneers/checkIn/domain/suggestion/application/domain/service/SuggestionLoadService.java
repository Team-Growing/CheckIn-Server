package dgsw.pioneers.checkIn.domain.suggestion.application.domain.service;

import dgsw.pioneers.checkIn.domain.suggestion.application.domain.model.Suggestion;
import dgsw.pioneers.checkIn.domain.suggestion.application.port.in.SuggestionLoadUseCase;
import dgsw.pioneers.checkIn.domain.suggestion.application.port.out.LoadSuggestionPort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SuggestionLoadService implements SuggestionLoadUseCase {

    private final LoadSuggestionPort loadSuggestionPort;

    @Override
    public List<Suggestion> loadAllSuggestion() {
        return loadSuggestionPort.loadAllSuggestion();
    }
}
