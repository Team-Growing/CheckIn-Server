package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class LectureIdJpaVO implements Serializable {

    @Column(name = "lecture_id")
    private Long id;
}
