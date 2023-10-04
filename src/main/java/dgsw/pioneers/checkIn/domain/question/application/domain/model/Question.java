package dgsw.pioneers.checkIn.domain.question.application.domain.model;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.question.application.domain.model.enums.QuestionStatus;
import dgsw.pioneers.checkIn.global.lib.zonedatetime.ZoneDateTimeUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Question {

    private final QuestionId questionId;

    private QuestionStatus questionStatus;

    private final String title;

    private final String content;

    private final LocalDateTime createdAt;

    private Questioner questioner;

    @Value
    @AllArgsConstructor
    public static class QuestionId {
        Long value;
    }

    public static Question withId(
             QuestionId questionId,
             QuestionStatus questionStatus,
             String title,
             String content,
             LocalDateTime createdAt,
             Questioner questioner) {
        return new Question(questionId, questionStatus, title, content, createdAt, questioner);
    }

    public static Question generate(String title, String content, Member.MemberId memberId) {
        return new Question(
                null,
                QuestionStatus.NOT_CONFIRMED,
                title,
                content,
                ZoneDateTimeUtil.nowToLocalDateTime(),
                Questioner.builder()
                        .memberId(memberId)
                        .build()
        );
    }
}
