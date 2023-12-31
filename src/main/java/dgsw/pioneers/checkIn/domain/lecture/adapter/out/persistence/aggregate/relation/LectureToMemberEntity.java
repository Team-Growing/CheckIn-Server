package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.relation;

import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.LectureJpaEntity;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.MemberJpaEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "lecture_to_member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class LectureToMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private LectureJpaEntity lectureJpaEntity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberJpaEntity memberJpaEntity;

    @NotNull
    private LocalDateTime applyDateTime;

    @Builder
    public LectureToMemberEntity(LectureJpaEntity lectureJpaEntity, MemberJpaEntity memberJpaEntity, LocalDateTime applyDateTime) {
        this.lectureJpaEntity = lectureJpaEntity;
        this.memberJpaEntity = memberJpaEntity;
        this.applyDateTime = applyDateTime;
    }
}
