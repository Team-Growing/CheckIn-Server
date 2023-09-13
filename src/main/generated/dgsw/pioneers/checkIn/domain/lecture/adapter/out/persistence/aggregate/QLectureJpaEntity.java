package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLectureJpaEntity is a Querydsl query type for LectureJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLectureJpaEntity extends EntityPathBase<LectureJpaEntity> {

    private static final long serialVersionUID = 2005328872L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QLectureJpaEntity lectureJpaEntity = new QLectureJpaEntity("lectureJpaEntity");

    public final dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo.QAcceptableStudentJpaVO acceptableStudent;

    public final NumberPath<Integer> enrollStudent = createNumber("enrollStudent", Integer.class);

    public final StringPath explanation = createString("explanation");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lectureName = createString("lectureName");

    public final dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo.QLectureScheduleJpaVO lectureSchedule;

    public final EnumPath<dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus> lectureStatus = createEnum("lectureStatus", dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus.class);

    public final EnumPath<dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureTag> lectureTag = createEnum("lectureTag", dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureTag.class);

    public final ListPath<dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.relation.LectureToMemberEntity, dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.relation.QLectureToMemberEntity> participants = this.<dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.relation.LectureToMemberEntity, dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.relation.QLectureToMemberEntity>createList("participants", dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.relation.LectureToMemberEntity.class, dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.relation.QLectureToMemberEntity.class, PathInits.DIRECT2);

    public final EnumPath<dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.PlaceType> placeType = createEnum("placeType", dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.PlaceType.class);

    public final StringPath teacherId = createString("teacherId");

    public final ListPath<WeekPlanJpaEntity, QWeekPlanJpaEntity> weekPlans = this.<WeekPlanJpaEntity, QWeekPlanJpaEntity>createList("weekPlans", WeekPlanJpaEntity.class, QWeekPlanJpaEntity.class, PathInits.DIRECT2);

    public QLectureJpaEntity(String variable) {
        this(LectureJpaEntity.class, forVariable(variable), INITS);
    }

    public QLectureJpaEntity(Path<? extends LectureJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QLectureJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QLectureJpaEntity(PathMetadata metadata, PathInits inits) {
        this(LectureJpaEntity.class, metadata, inits);
    }

    public QLectureJpaEntity(Class<? extends LectureJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.acceptableStudent = inits.isInitialized("acceptableStudent") ? new dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo.QAcceptableStudentJpaVO(forProperty("acceptableStudent")) : null;
        this.lectureSchedule = inits.isInitialized("lectureSchedule") ? new dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo.QLectureScheduleJpaVO(forProperty("lectureSchedule")) : null;
    }

}

