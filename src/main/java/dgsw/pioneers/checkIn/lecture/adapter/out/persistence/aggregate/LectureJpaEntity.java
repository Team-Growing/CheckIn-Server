package dgsw.pioneers.checkIn.lecture.adapter.out.persistence.aggregate;

import dgsw.pioneers.checkIn.lecture.adapter.out.persistence.aggregate.vo.AcceptableStudentJpaVO;
import dgsw.pioneers.checkIn.lecture.adapter.out.persistence.aggregate.vo.LectureScheduleJpaVO;
import dgsw.pioneers.checkIn.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.lecture.application.domain.model.enums.PlaceType;
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

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name = "lecture_id")
    private List<WeekPlanJpaEntity> weekPlans;

    @Builder
    public LectureJpaEntity(String explanation, LectureStatus lectureStatus, PlaceType placeType, String teacherId, AcceptableStudentJpaVO acceptableStudent, LectureScheduleJpaVO lectureSchedule) {
        this.explanation = explanation;
        this.lectureStatus = lectureStatus;
        this.placeType = placeType;
        this.teacherId = teacherId;
        this.acceptableStudent = acceptableStudent;
        this.lectureSchedule = lectureSchedule;
    }
}
