package dgsw.pioneers.checkIn.domain.suggestion.application.domain.service;

import dgsw.pioneers.checkIn.domain.suggestion.application.domain.model.Suggestion;
import dgsw.pioneers.checkIn.domain.suggestion.application.port.in.SuggestionGenerateUseCase;
import dgsw.pioneers.checkIn.domain.suggestion.application.port.out.CreateSuggestionPort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SuggestionGenerateService implements SuggestionGenerateUseCase {

    private final CreateSuggestionPort createSuggestionPort;

    @Override
    @Transactional
    public void generateSuggestion(Suggestion suggestion) {

        createSuggestionPort.createSuggestion(suggestion);
    }
}
