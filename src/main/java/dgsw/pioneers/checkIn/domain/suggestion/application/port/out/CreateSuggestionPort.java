package dgsw.pioneers.checkIn.domain.suggestion.application.port.out;

import dgsw.pioneers.checkIn.domain.suggestion.application.domain.model.Suggestion;

public interface CreateSuggestionPort {

    void createSuggestion(Suggestion suggestion);
}
