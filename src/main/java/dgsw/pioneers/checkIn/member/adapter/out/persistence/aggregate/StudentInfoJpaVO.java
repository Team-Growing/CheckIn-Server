package dgsw.pioneers.checkIn.member.adapter.out.persistence.aggregate;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentInfoJpaVO {

    private int year;
    private int grade;
    private int room;
    private int number;
}
