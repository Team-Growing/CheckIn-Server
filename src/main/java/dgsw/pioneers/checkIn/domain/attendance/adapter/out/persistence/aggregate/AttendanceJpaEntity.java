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
    private String code;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private LectureJpaEntity lecture;

    @Builder
    public AttendanceJpaEntity(AttendanceStatus attendanceStatus, LocalDate lectureDate, int attendStudent, String code, LectureJpaEntity lecture) {
        this.attendanceStatus = attendanceStatus;
        this.lectureDate = lectureDate;
        this.attendStudent = attendStudent;
        this.lecture = lecture;
        this.code = code;
    }

    public void updateCode(String code) {
        this.code = code;
    }
}
