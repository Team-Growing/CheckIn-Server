package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QAcceptableStudentJpaVO is a Querydsl query type for AcceptableStudentJpaVO
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QAcceptableStudentJpaVO extends BeanPath<AcceptableStudentJpaVO> {

    private static final long serialVersionUID = -587367992L;

    public static final QAcceptableStudentJpaVO acceptableStudentJpaVO = new QAcceptableStudentJpaVO("acceptableStudentJpaVO");

    public final NumberPath<Integer> maxStudent = createNumber("maxStudent", Integer.class);

    public final NumberPath<Integer> minStudent = createNumber("minStudent", Integer.class);

    public final NumberPath<Integer> targetGrade = createNumber("targetGrade", Integer.class);

    public QAcceptableStudentJpaVO(String variable) {
        super(AcceptableStudentJpaVO.class, forVariable(variable));
    }

    public QAcceptableStudentJpaVO(Path<? extends AcceptableStudentJpaVO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAcceptableStudentJpaVO(PathMetadata metadata) {
        super(AcceptableStudentJpaVO.class, metadata);
    }

}

