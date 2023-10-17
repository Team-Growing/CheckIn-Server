package dgsw.pioneers.checkIn.domain.suggestion.adapter.in.web.dto.res;

import dgsw.pioneers.checkIn.domain.suggestion.application.domain.model.Suggestion;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SuggestionWithTotalCountRes {

    private int totalCount;
    private List<Suggestion> value;
}
