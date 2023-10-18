package dgsw.pioneers.checkIn.domain.suggestion.application.port.in;

import dgsw.pioneers.checkIn.domain.suggestion.adapter.in.web.dto.res.SuggestionWithTotalCountRes;
import dgsw.pioneers.checkIn.domain.suggestion.application.domain.model.Suggestion;

public interface SuggestionLoadUseCase {

    SuggestionWithTotalCountRes loadSuggestion(int page, int limit);
    Suggestion loadSuggestionById(Suggestion.SuggestionId suggestionId);
}
