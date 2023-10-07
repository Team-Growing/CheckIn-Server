package dgsw.pioneers.checkIn.domain.suggestion.application.domain.model;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.global.lib.zonedatetime.ZoneDateTimeUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Suggestion {

    private final SuggestionId suggestionId;

    private final String name;

    private final String content;

    private final DayOfWeek dayOfWeek;

    private final String additional;

    private final LocalDateTime createdAt;

    private Suggester suggester;

    @Value
    @AllArgsConstructor
    public static class SuggestionId {
        Long value;
    }

    public static Suggestion withId(
            SuggestionId suggestionId,
            String name,
            String content,
            DayOfWeek dayOfWeek,
            String additional,
            LocalDateTime createdAt,
            Suggester suggester) {
        return new Suggestion(suggestionId, name, content, dayOfWeek, additional, createdAt, suggester);
    }

    public static Suggestion generate(String name, String content, DayOfWeek dayOfWeek, String additional, Member.MemberId memberId) {
        return new Suggestion(
                null,
                name,
                content,
                dayOfWeek,
                additional,
                ZoneDateTimeUtil.nowToLocalDateTime(),
                Suggester.builder()
                        .memberId(memberId)
                        .build()
        );
    }
}
