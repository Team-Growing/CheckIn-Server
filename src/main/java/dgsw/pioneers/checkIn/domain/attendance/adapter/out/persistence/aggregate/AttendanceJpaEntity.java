package dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.LectureJpaEntity;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.WeekPlanJpaEntity;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Entity
@Table(name = "attendance")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
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

    @OneToMany(mappedBy = "attendanceJpaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttendantJpaEntity> attendants;

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

    public void updateAttendStudent(int attendStudent) {
        this.attendStudent = attendStudent;
    }

    public void addAttendant(AttendantJpaEntity attendantJpa) {
        this.attendants.add(attendantJpa);
    }
}
