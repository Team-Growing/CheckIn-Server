package dgsw.pioneers.checkIn.domain.notice.adapter.out.persistence.aggregate;

import dgsw.pioneers.checkIn.domain.notice.application.domain.model.enums.NoticeStatus;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "notice")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class NoticeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private NoticeStatus noticeStatus;

    @NotNull
    private String content;

    @NotNull
    private LocalDateTime createdAt;

    @Builder
    public NoticeJpaEntity(NoticeStatus noticeStatus, String content, LocalDateTime createdAt) {
        this.noticeStatus = noticeStatus;
        this.content = content;
        this.createdAt = createdAt;
    }
}
