package dgsw.pioneers.checkIn.domain.suggestion.application.port.in;

import dgsw.pioneers.checkIn.domain.suggestion.application.domain.model.Suggestion;

import java.util.List;

public interface SuggestionLoadUseCase {

    List<Suggestion> loadAllSuggestion();
}
