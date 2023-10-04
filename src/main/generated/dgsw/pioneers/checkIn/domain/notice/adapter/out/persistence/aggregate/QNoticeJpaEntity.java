package dgsw.pioneers.checkIn.domain.notice.adapter.out.persistence.aggregate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QNoticeJpaEntity is a Querydsl query type for NoticeJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QNoticeJpaEntity extends EntityPathBase<NoticeJpaEntity> {

    private static final long serialVersionUID = -2105312402L;

    public static final QNoticeJpaEntity noticeJpaEntity = new QNoticeJpaEntity("noticeJpaEntity");

    public final StringPath content = createString("content");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<dgsw.pioneers.checkIn.domain.notice.application.domain.model.enums.NoticeStatus> noticeStatus = createEnum("noticeStatus", dgsw.pioneers.checkIn.domain.notice.application.domain.model.enums.NoticeStatus.class);

    public QNoticeJpaEntity(String variable) {
        super(NoticeJpaEntity.class, forVariable(variable));
    }

    public QNoticeJpaEntity(Path<? extends NoticeJpaEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QNoticeJpaEntity(PathMetadata metadata) {
        super(NoticeJpaEntity.class, metadata);
    }

}

