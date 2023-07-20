package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo.AcceptableStudentJpaVO;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo.LectureScheduleJpaVO;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.PlaceType;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Entity
@Table(name = "lecture")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String explanation;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LectureStatus lectureStatus;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PlaceType placeType;

    @NotNull
    @Column(name = "fk_teacher_id")
    private String teacherId;

    @Embedded
    private AcceptableStudentJpaVO acceptableStudent;

    @Embedded
    private LectureScheduleJpaVO lectureSchedule;

    @NotNull
    private int enrollStudent;

    @OneToMany(mappedBy = "lectureJpaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WeekPlanJpaEntity> weekPlans;

    @Builder
    public LectureJpaEntity(String explanation, LectureStatus lectureStatus, PlaceType placeType, String teacherId, AcceptableStudentJpaVO acceptableStudent, LectureScheduleJpaVO lectureSchedule, int enrollStudent) {
        this.explanation = explanation;
        this.lectureStatus = lectureStatus;
        this.placeType = placeType;
        this.teacherId = teacherId;
        this.acceptableStudent = acceptableStudent;
        this.lectureSchedule = lectureSchedule;
        this.enrollStudent = enrollStudent;
    }

    public void addWeekPlans(WeekPlanJpaEntity weekPlan) {
        this.weekPlans.add(weekPlan);
    }
}
