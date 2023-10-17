package dgsw.pioneers.checkIn.domain.question.adapter.in.web.dto.res;

import dgsw.pioneers.checkIn.domain.question.application.domain.model.Question;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QuestionWithTotalCountRes {

    private int totalCount;
    private List<Question> value;
}
