package dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAttendanceIdJpaVO is a Querydsl query type for AttendanceIdJpaVO
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAttendanceIdJpaVO extends BeanPath<AttendanceIdJpaVO> {

    private static final long serialVersionUID = 877369250L;

    public static final QAttendanceIdJpaVO attendanceIdJpaVO = new QAttendanceIdJpaVO("attendanceIdJpaVO");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QAttendanceIdJpaVO(String variable) {
        super(AttendanceIdJpaVO.class, forVariable(variable));
    }

    public QAttendanceIdJpaVO(Path<? extends AttendanceIdJpaVO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAttendanceIdJpaVO(PathMetadata metadata) {
        super(AttendanceIdJpaVO.class, metadata);
    }

}

