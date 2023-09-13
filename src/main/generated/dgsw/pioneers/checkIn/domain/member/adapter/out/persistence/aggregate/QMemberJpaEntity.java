package dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberJpaEntity is a Querydsl query type for MemberJpaEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberJpaEntity extends EntityPathBase<MemberJpaEntity> {

    private static final long serialVersionUID = -1007263442L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberJpaEntity memberJpaEntity = new QMemberJpaEntity("memberJpaEntity");

    public final StringPath email = createString("email");

    public final StringPath id = createString("id");

    public final EnumPath<dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole> memberRole = createEnum("memberRole", dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole.class);

    public final StringPath name = createString("name");

    public final StringPath pw = createString("pw");

    public final dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.vo.QStudentInfoJpaVO studentInfo;

    public final StringPath subject = createString("subject");

    public QMemberJpaEntity(String variable) {
        this(MemberJpaEntity.class, forVariable(variable), INITS);
    }

    public QMemberJpaEntity(Path<? extends MemberJpaEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberJpaEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberJpaEntity(PathMetadata metadata, PathInits inits) {
        this(MemberJpaEntity.class, metadata, inits);
    }

    public QMemberJpaEntity(Class<? extends MemberJpaEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.studentInfo = inits.isInitialized("studentInfo") ? new dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.vo.QStudentInfoJpaVO(forProperty("studentInfo")) : null;
    }

}

