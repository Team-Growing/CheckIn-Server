package dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAttendanceJpaEntity is a Querydsl query type for AttendanceJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttendanceJpaEntity extends EntityPathBase<AttendanceJpaEntity> {

    private static final long serialVersionUID = 1389030990L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAttendanceJpaEntity attendanceJpaEntity = new QAttendanceJpaEntity("attendanceJpaEntity");

    public final EnumPath<dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus> attendanceStatus = createEnum("attendanceStatus", dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus.class);

    public final ListPath<AttendantJpaEntity, QAttendantJpaEntity> attendants = this.<AttendantJpaEntity, QAttendantJpaEntity>createList("attendants", AttendantJpaEntity.class, QAttendantJpaEntity.class, PathInits.DIRECT2);

    public final NumberPath<Integer> attendStudent = createNumber("attendStudent", Integer.class);

    public final StringPath code = createString("code");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.QLectureJpaEntity lecture;

    public final DatePath<java.time.LocalDate> lectureDate = createDate("lectureDate", java.time.LocalDate.class);

    public QAttendanceJpaEntity(String variable) {
        this(AttendanceJpaEntity.class, forVariable(variable), INITS);
    }

    public QAttendanceJpaEntity(Path<? extends AttendanceJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAttendanceJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAttendanceJpaEntity(PathMetadata metadata, PathInits inits) {
        this(AttendanceJpaEntity.class, metadata, inits);
    }

    public QAttendanceJpaEntity(Class<? extends AttendanceJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lecture = inits.isInitialized("lecture") ? new dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.QLectureJpaEntity(forProperty("lecture"), inits.get("lecture")) : null;
    }

}

