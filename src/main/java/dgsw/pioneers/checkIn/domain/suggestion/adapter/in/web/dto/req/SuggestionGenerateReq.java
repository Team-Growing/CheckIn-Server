package dgsw.pioneers.checkIn.domain.suggestion.adapter.in.web.dto.req;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.suggestion.application.domain.model.Suggestion;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;

@Getter
public class SuggestionGenerateReq {

    @NotBlank
    private String name;

    @NotBlank
    private String content;

    @NotNull
    private DayOfWeek dayOfWeek;

    private String additional;

    public Suggestion mapToDomainEntity(Member.MemberId memberId) {
        return Suggestion.generate(this.name, this.content, this.dayOfWeek, this.additional, memberId);
    }
}
