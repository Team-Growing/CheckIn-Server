package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate;

import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.relation.LectureToMemberEntity;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo.DayOfWeekVO;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo.AcceptableStudentJpaVO;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo.LectureScheduleJpaVO;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureTag;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.PlaceType;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Getter
@Entity
@Table(name = "lecture")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
public class LectureJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String lectureName;

    @NotNull
    private String explanation;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LectureStatus lectureStatus;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PlaceType placeType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private LectureTag lectureTag;

    @NotNull
    @Column(name = "fk_teacher_id")
    private String teacherId;

    @Embedded
    private AcceptableStudentJpaVO acceptableStudent;

    @Embedded
    private LectureScheduleJpaVO lectureSchedule;

    @ElementCollection
    @CollectionTable(
            name = "lecture_of_dayOfWeek",
            joinColumns = @JoinColumn(name = "lecture_id")
    )
    private Set<DayOfWeekVO> dayOfWeekVO;

    @NotNull
    private int enrollStudent;

    @OneToMany(mappedBy = "lectureJpaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WeekPlanJpaEntity> weekPlans;

    @OneToMany(mappedBy = "lectureJpaEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LectureToMemberEntity> participants;

    @Builder
    public LectureJpaEntity(String lectureName, String explanation, LectureStatus lectureStatus, PlaceType placeType, LectureTag lectureTag, String teacherId, AcceptableStudentJpaVO acceptableStudent, LectureScheduleJpaVO lectureSchedule, int enrollStudent, Set<DayOfWeekVO> dayOfWeekVO) {
        this.lectureName = lectureName;
        this.explanation = explanation;
        this.lectureStatus = lectureStatus;
        this.placeType = placeType;
        this.teacherId = teacherId;
        this.acceptableStudent = acceptableStudent;
        this.lectureSchedule = lectureSchedule;
        this.enrollStudent = enrollStudent;
        this.lectureTag = lectureTag;
        this.dayOfWeekVO = dayOfWeekVO;
    }

    public void addWeekPlans(WeekPlanJpaEntity weekPlan) {
        this.weekPlans.add(weekPlan);
    }

    public void updateEnrollStudent(int enrollStudent) {
        this.enrollStudent = enrollStudent;
    }

    public void updateLectureStatus(LectureStatus lectureStatus) {
        this.lectureStatus = lectureStatus;
    }
}
