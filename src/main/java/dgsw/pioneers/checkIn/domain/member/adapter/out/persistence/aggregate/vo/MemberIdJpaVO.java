package dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.vo;

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
public class MemberIdJpaVO implements Serializable {

    @Column(name = "member_id")
    private String id;
}
