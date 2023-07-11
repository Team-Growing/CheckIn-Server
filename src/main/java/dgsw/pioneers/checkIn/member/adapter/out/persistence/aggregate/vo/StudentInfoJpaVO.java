package dgsw.pioneers.checkIn.member.adapter.out.persistence.aggregate.vo;

import lombok.*;

import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentInfoJpaVO {

    private int year;
    private int grade;
    private int room;
    private int number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentInfoJpaVO studentInfo = (StudentInfoJpaVO) o;
        return Objects.equals(year, studentInfo.year) && Objects.equals(grade, studentInfo.grade)
                && Objects.equals(room, studentInfo.room) && Objects.equals(number, studentInfo.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, grade, room, number);
    }
}
