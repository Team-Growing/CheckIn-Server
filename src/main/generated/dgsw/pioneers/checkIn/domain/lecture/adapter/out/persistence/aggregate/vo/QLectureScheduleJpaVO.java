package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLectureScheduleJpaVO is a Querydsl query type for LectureScheduleJpaVO
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QLectureScheduleJpaVO extends BeanPath<LectureScheduleJpaVO> {

    private static final long serialVersionUID = 386695628L;

    public static final QLectureScheduleJpaVO lectureScheduleJpaVO = new QLectureScheduleJpaVO("lectureScheduleJpaVO");

    public final DatePath<java.time.LocalDate> EndDay = createDate("EndDay", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> endDay = createDate("endDay", java.time.LocalDate.class);

    public final TimePath<java.time.LocalTime> endTime = createTime("endTime", java.time.LocalTime.class);

    public final DatePath<java.time.LocalDate> startDay = createDate("startDay", java.time.LocalDate.class);

    public final TimePath<java.time.LocalTime> startTime = createTime("startTime", java.time.LocalTime.class);

    public QLectureScheduleJpaVO(String variable) {
        super(LectureScheduleJpaVO.class, forVariable(variable));
    }

    public QLectureScheduleJpaVO(Path<? extends LectureScheduleJpaVO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLectureScheduleJpaVO(PathMetadata metadata) {
        super(LectureScheduleJpaVO.class, metadata);
    }

}

