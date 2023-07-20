package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo;

import lombok.*;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AcceptableStudentJpaVO {

    private int maxStudent;
    private int minStudent;
    private int targetGrade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcceptableStudentJpaVO studentInfo = (AcceptableStudentJpaVO) o;
        return Objects.equals(maxStudent, studentInfo.maxStudent) && Objects.equals(minStudent, studentInfo.minStudent) && Objects.equals(targetGrade, studentInfo.targetGrade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxStudent, minStudent, targetGrade);
    }
}
