package dgsw.pioneers.checkIn.domain.suggestion.application.port.in;

import dgsw.pioneers.checkIn.domain.suggestion.application.domain.model.Suggestion;

public interface SuggestionGenerateUseCase {

    void generateSuggestion(Suggestion suggestion);
}
