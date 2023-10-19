package dgsw.pioneers.checkIn.domain.absence.adapter.out.persistence.aggregate;

import dgsw.pioneers.checkIn.domain.absence.application.domain.model.enums.AbsenceStatus;
import dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate.vo.AttendanceIdJpaVO;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo.LectureIdJpaVO;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.MemberJpaEntity;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "absence")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class AbsenceJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AbsenceStatus absenceStatus;

    @NotNull
    private String reason;

    @NotNull
    private LocalDateTime createdAt;

    @NotNull
    @Embedded
    private LectureIdJpaVO lectureId;

    @NotNull
    @Embedded
    private AttendanceIdJpaVO attendanceId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberJpaEntity memberJpaEntity;

    @Builder
    public AbsenceJpaEntity(AbsenceStatus absenceStatus, String reason, LocalDateTime createdAt, LectureIdJpaVO lectureId, AttendanceIdJpaVO attendanceId, MemberJpaEntity memberJpaEntity) {
        this.absenceStatus = absenceStatus;
        this.reason = reason;
        this.createdAt = createdAt;
        this.lectureId = lectureId;
        this.attendanceId = attendanceId;
        this.memberJpaEntity = memberJpaEntity;
    }

    public void updateAbsenceStatus(AbsenceStatus absenceStatus) {
        this.absenceStatus = absenceStatus;
    }
}
