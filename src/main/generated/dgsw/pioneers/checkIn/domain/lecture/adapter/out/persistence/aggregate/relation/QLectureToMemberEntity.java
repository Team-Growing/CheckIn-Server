package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.relation;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLectureToMemberEntity is a Querydsl query type for LectureToMemberEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLectureToMemberEntity extends EntityPathBase<LectureToMemberEntity> {

    private static final long serialVersionUID = -1959720500L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLectureToMemberEntity lectureToMemberEntity = new QLectureToMemberEntity("lectureToMemberEntity");

    public final DateTimePath<java.time.LocalDateTime> applyDateTime = createDateTime("applyDateTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.QLectureJpaEntity lectureJpaEntity;

    public final dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.QMemberJpaEntity memberJpaEntity;

    public QLectureToMemberEntity(String variable) {
        this(LectureToMemberEntity.class, forVariable(variable), INITS);
    }

    public QLectureToMemberEntity(Path<? extends LectureToMemberEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLectureToMemberEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLectureToMemberEntity(PathMetadata metadata, PathInits inits) {
        this(LectureToMemberEntity.class, metadata, inits);
    }

    public QLectureToMemberEntity(Class<? extends LectureToMemberEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lectureJpaEntity = inits.isInitialized("lectureJpaEntity") ? new dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.QLectureJpaEntity(forProperty("lectureJpaEntity"), inits.get("lectureJpaEntity")) : null;
        this.memberJpaEntity = inits.isInitialized("memberJpaEntity") ? new dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.QMemberJpaEntity(forProperty("memberJpaEntity"), inits.get("memberJpaEntity")) : null;
    }

}

