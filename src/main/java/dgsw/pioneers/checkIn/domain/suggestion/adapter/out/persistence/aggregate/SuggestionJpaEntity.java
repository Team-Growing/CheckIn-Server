package dgsw.pioneers.checkIn.domain.suggestion.adapter.out.persistence.aggregate;

import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.MemberJpaEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "suggestion")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SuggestionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String content;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    private String additional;

    @NotNull
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberJpaEntity member;

    @Builder
    public SuggestionJpaEntity(String name, String content, DayOfWeek dayOfWeek, String additional, LocalDateTime createdAt, MemberJpaEntity memberJpaEntity) {
        this.name = name;
        this.content = content;
        this.dayOfWeek = dayOfWeek;
        this.additional = additional;
        this.createdAt = createdAt;
        this.member = memberJpaEntity;
    }
}
