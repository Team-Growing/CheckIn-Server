package dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.LectureJpaEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "attendance")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttendanceJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AttendanceStatus attendanceStatus;

    @NotNull
    private LocalDate lectureDate;

    @NotNull
    private int attendStudent;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private LectureJpaEntity lecture;

    @Builder
    public AttendanceJpaEntity(AttendanceStatus attendanceStatus, LocalDate lectureDate, int attendStudent, LectureJpaEntity lecture) {
        this.attendanceStatus = attendanceStatus;
        this.lectureDate = lectureDate;
        this.attendStudent = attendStudent;
        this.lecture = lecture;
    }
}
