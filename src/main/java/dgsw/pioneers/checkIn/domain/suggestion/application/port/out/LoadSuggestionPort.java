package dgsw.pioneers.checkIn.domain.suggestion.application.port.out;

import dgsw.pioneers.checkIn.domain.suggestion.application.domain.model.Suggestion;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface LoadSuggestionPort {

    Suggestion loadSuggestionById(Suggestion.SuggestionId suggestionId);
    List<Suggestion> loadAllSuggestion(PageRequest pageRequest);
}
