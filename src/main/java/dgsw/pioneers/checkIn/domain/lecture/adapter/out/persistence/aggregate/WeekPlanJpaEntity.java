package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@Table(name = "lecture_week_plan")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WeekPlanJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int week;
    private String introduction;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private LectureJpaEntity lectureJpaEntity;
}
