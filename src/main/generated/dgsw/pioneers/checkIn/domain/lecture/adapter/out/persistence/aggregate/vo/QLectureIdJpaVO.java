package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QLectureIdJpaVO is a Querydsl query type for LectureIdJpaVO
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QLectureIdJpaVO extends BeanPath<LectureIdJpaVO> {

    private static final long serialVersionUID = -1908498168L;

    public static final QLectureIdJpaVO lectureIdJpaVO = new QLectureIdJpaVO("lectureIdJpaVO");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QLectureIdJpaVO(String variable) {
        super(LectureIdJpaVO.class, forVariable(variable));
    }

    public QLectureIdJpaVO(Path<? extends LectureIdJpaVO> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLectureIdJpaVO(PathMetadata metadata) {
        super(LectureIdJpaVO.class, metadata);
    }

}

