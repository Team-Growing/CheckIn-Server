package dgsw.pioneers.checkIn.domain.absence.adapter.out.persistence.aggregate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAbsenceJpaEntity is a Querydsl query type for AbsenceJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAbsenceJpaEntity extends EntityPathBase<AbsenceJpaEntity> {

    private static final long serialVersionUID = 418680554L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAbsenceJpaEntity absenceJpaEntity = new QAbsenceJpaEntity("absenceJpaEntity");

    public final EnumPath<dgsw.pioneers.checkIn.domain.absence.application.domain.model.enums.AbsenceStatus> absenceStatus = createEnum("absenceStatus", dgsw.pioneers.checkIn.domain.absence.application.domain.model.enums.AbsenceStatus.class);

    public final dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate.vo.QAttendanceIdJpaVO attendanceId;

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo.QLectureIdJpaVO lectureId;

    public final dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.QMemberJpaEntity memberJpaEntity;

    public final StringPath reason = createString("reason");

    public QAbsenceJpaEntity(String variable) {
        this(AbsenceJpaEntity.class, forVariable(variable), INITS);
    }

    public QAbsenceJpaEntity(Path<? extends AbsenceJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAbsenceJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAbsenceJpaEntity(PathMetadata metadata, PathInits inits) {
        this(AbsenceJpaEntity.class, metadata, inits);
    }

    public QAbsenceJpaEntity(Class<? extends AbsenceJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.attendanceId = inits.isInitialized("attendanceId") ? new dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate.vo.QAttendanceIdJpaVO(forProperty("attendanceId")) : null;
        this.lectureId = inits.isInitialized("lectureId") ? new dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo.QLectureIdJpaVO(forProperty("lectureId")) : null;
        this.memberJpaEntity = inits.isInitialized("memberJpaEntity") ? new dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.QMemberJpaEntity(forProperty("memberJpaEntity"), inits.get("memberJpaEntity")) : null;
    }

}

