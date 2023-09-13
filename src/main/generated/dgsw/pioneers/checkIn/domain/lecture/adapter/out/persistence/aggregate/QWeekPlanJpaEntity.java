package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWeekPlanJpaEntity is a Querydsl query type for WeekPlanJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWeekPlanJpaEntity extends EntityPathBase<WeekPlanJpaEntity> {

    private static final long serialVersionUID = -1440389191L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWeekPlanJpaEntity weekPlanJpaEntity = new QWeekPlanJpaEntity("weekPlanJpaEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath introduction = createString("introduction");

    public final QLectureJpaEntity lectureJpaEntity;

    public final NumberPath<Integer> week = createNumber("week", Integer.class);

    public QWeekPlanJpaEntity(String variable) {
        this(WeekPlanJpaEntity.class, forVariable(variable), INITS);
    }

    public QWeekPlanJpaEntity(Path<? extends WeekPlanJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWeekPlanJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWeekPlanJpaEntity(PathMetadata metadata, PathInits inits) {
        this(WeekPlanJpaEntity.class, metadata, inits);
    }

    public QWeekPlanJpaEntity(Class<? extends WeekPlanJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.lectureJpaEntity = inits.isInitialized("lectureJpaEntity") ? new QLectureJpaEntity(forProperty("lectureJpaEntity"), inits.get("lectureJpaEntity")) : null;
    }

}

