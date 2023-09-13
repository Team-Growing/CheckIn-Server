package dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStudentInfoJpaVO is a Querydsl query type for StudentInfoJpaVO
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QStudentInfoJpaVO extends BeanPath<StudentInfoJpaVO> {

    private static final long serialVersionUID = 1870331050L;

    public static final QStudentInfoJpaVO studentInfoJpaVO = new QStudentInfoJpaVO("studentInfoJpaVO");

    public final NumberPath<Integer> grade = createNumber("grade", Integer.class);

    public final NumberPath<Integer> number = createNumber("number", Integer.class);

    public final NumberPath<Integer> room = createNumber("room", Integer.class);

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QStudentInfoJpaVO(String variable) {
        super(StudentInfoJpaVO.class, forVariable(variable));
    }

    public QStudentInfoJpaVO(Path<? extends StudentInfoJpaVO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStudentInfoJpaVO(PathMetadata metadata) {
        super(StudentInfoJpaVO.class, metadata);
    }

}

