package dgsw.pioneers.checkIn.domain.suggestion.application.port.out;

import dgsw.pioneers.checkIn.domain.suggestion.application.domain.model.Suggestion;

import java.util.List;

public interface LoadSuggestionPort {

    List<Suggestion> loadAllSuggestion();
}
