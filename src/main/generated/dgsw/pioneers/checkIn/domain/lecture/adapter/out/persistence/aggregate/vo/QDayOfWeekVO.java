package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QDayOfWeekVO is a Querydsl query type for DayOfWeekVO
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QDayOfWeekVO extends BeanPath<DayOfWeekVO> {

    private static final long serialVersionUID = 1868557395L;

    public static final QDayOfWeekVO dayOfWeekVO = new QDayOfWeekVO("dayOfWeekVO");

    public final EnumPath<java.time.DayOfWeek> dayOfWeek = createEnum("dayOfWeek", java.time.DayOfWeek.class);

    public QDayOfWeekVO(String variable) {
        super(DayOfWeekVO.class, forVariable(variable));
    }

    public QDayOfWeekVO(Path<? extends DayOfWeekVO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QDayOfWeekVO(PathMetadata metadata) {
        super(DayOfWeekVO.class, metadata);
    }

}

