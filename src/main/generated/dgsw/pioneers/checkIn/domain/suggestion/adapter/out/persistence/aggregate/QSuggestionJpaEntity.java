package dgsw.pioneers.checkIn.domain.suggestion.adapter.out.persistence.aggregate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QSuggestionJpaEntity is a Querydsl query type for SuggestionJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QSuggestionJpaEntity extends EntityPathBase<SuggestionJpaEntity> {

    private static final long serialVersionUID = 781154926L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSuggestionJpaEntity suggestionJpaEntity = new QSuggestionJpaEntity("suggestionJpaEntity");

    public final StringPath additional = createString("additional");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final EnumPath<java.time.DayOfWeek> dayOfWeek = createEnum("dayOfWeek", java.time.DayOfWeek.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.QMemberJpaEntity member;

    public final StringPath name = createString("name");

    public QSuggestionJpaEntity(String variable) {
        this(SuggestionJpaEntity.class, forVariable(variable), INITS);
    }

    public QSuggestionJpaEntity(Path<? extends SuggestionJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QSuggestionJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QSuggestionJpaEntity(PathMetadata metadata, PathInits inits) {
        this(SuggestionJpaEntity.class, metadata, inits);
    }

    public QSuggestionJpaEntity(Class<? extends SuggestionJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.QMemberJpaEntity(forProperty("member"), inits.get("member")) : null;
    }

}

