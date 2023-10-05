package dgsw.pioneers.checkIn.domain.question.adapter.out.persistence.aggregate;

import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.MemberJpaEntity;
import dgsw.pioneers.checkIn.domain.question.application.domain.model.enums.QuestionStatus;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "question")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class QuestionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private QuestionStatus questionStatus;

    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_teacher_id")
    private MemberJpaEntity member;

    @Builder
    public QuestionJpaEntity(QuestionStatus questionStatus, String title, String content, LocalDateTime createdAt, MemberJpaEntity memberJpaEntity) {
        this.questionStatus = questionStatus;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.member = memberJpaEntity;
    }

    public void updateQuestionStatus(QuestionStatus questionStatus) {
        this.questionStatus = questionStatus;
    }
}
