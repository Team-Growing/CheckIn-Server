package dgsw.pioneers.checkIn.domain.question.adapter.out.persistence.aggregate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QQuestionJpaEntity is a Querydsl query type for QuestionJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QQuestionJpaEntity extends EntityPathBase<QuestionJpaEntity> {

    private static final long serialVersionUID = 314317678L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QQuestionJpaEntity questionJpaEntity = new QQuestionJpaEntity("questionJpaEntity");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.QMemberJpaEntity member;

    public final EnumPath<dgsw.pioneers.checkIn.domain.question.application.domain.model.enums.QuestionStatus> questionStatus = createEnum("questionStatus", dgsw.pioneers.checkIn.domain.question.application.domain.model.enums.QuestionStatus.class);

    public final StringPath title = createString("title");

    public QQuestionJpaEntity(String variable) {
        this(QuestionJpaEntity.class, forVariable(variable), INITS);
    }

    public QQuestionJpaEntity(Path<? extends QuestionJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QQuestionJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QQuestionJpaEntity(PathMetadata metadata, PathInits inits) {
        this(QuestionJpaEntity.class, metadata, inits);
    }

    public QQuestionJpaEntity(Class<? extends QuestionJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.QMemberJpaEntity(forProperty("member"), inits.get("member")) : null;
    }

}

