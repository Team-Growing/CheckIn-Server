package dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate;

import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.vo.MemberIdJpaVO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "attendant")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttendantJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Embedded
    @AttributeOverrides(
            @AttributeOverride(name = "id",
                    column = @Column(name = "attendant_id"))
    )
    private MemberIdJpaVO memberId;

    @NotNull
    private LocalDateTime applyDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendance_id")
    private AttendanceJpaEntity attendanceJpaEntity;

    @Builder
    public AttendantJpaEntity(MemberIdJpaVO memberId, LocalDateTime applyDateTime, AttendanceJpaEntity attendanceJpa) {
        this.memberId = memberId;
        this.applyDateTime = applyDateTime;
        this.attendanceJpaEntity = attendanceJpa;
    }
}
