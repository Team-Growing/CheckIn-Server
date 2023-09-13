package dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAttendantJpaEntity is a Querydsl query type for AttendantJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAttendantJpaEntity extends EntityPathBase<AttendantJpaEntity> {

    private static final long serialVersionUID = -1543887272L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAttendantJpaEntity attendantJpaEntity = new QAttendantJpaEntity("attendantJpaEntity");

    public final DateTimePath<java.time.LocalDateTime> applyDateTime = createDateTime("applyDateTime", java.time.LocalDateTime.class);

    public final QAttendanceJpaEntity attendanceJpaEntity;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.vo.QMemberIdJpaVO memberId;

    public QAttendantJpaEntity(String variable) {
        this(AttendantJpaEntity.class, forVariable(variable), INITS);
    }

    public QAttendantJpaEntity(Path<? extends AttendantJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAttendantJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAttendantJpaEntity(PathMetadata metadata, PathInits inits) {
        this(AttendantJpaEntity.class, metadata, inits);
    }

    public QAttendantJpaEntity(Class<? extends AttendantJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.attendanceJpaEntity = inits.isInitialized("attendanceJpaEntity") ? new QAttendanceJpaEntity(forProperty("attendanceJpaEntity"), inits.get("attendanceJpaEntity")) : null;
        this.memberId = inits.isInitialized("memberId") ? new dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.vo.QMemberIdJpaVO(forProperty("memberId")) : null;
    }

}

