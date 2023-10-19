package dgsw.pioneers.checkIn.domain.attendance.adapter.out.persistence.aggregate.vo;

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
public class AttendanceIdJpaVO implements Serializable {

    @Column(name = "attendance_id")
    private Long id;
}
