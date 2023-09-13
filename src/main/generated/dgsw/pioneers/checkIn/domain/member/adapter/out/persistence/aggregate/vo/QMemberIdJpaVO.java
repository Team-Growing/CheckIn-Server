package dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberIdJpaVO is a Querydsl query type for MemberIdJpaVO
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QMemberIdJpaVO extends BeanPath<MemberIdJpaVO> {

    private static final long serialVersionUID = 2098863872L;

    public static final QMemberIdJpaVO memberIdJpaVO = new QMemberIdJpaVO("memberIdJpaVO");

    public final StringPath id = createString("id");

    public QMemberIdJpaVO(String variable) {
        super(MemberIdJpaVO.class, forVariable(variable));
    }

    public QMemberIdJpaVO(Path<? extends MemberIdJpaVO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberIdJpaVO(PathMetadata metadata) {
        super(MemberIdJpaVO.class, metadata);
    }

}

