package dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate;

import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.vo.StudentInfoJpaVO;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@Builder
@Table(name = "member")
@SecondaryTable(
        name = "student_of_info",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "id")
)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberJpaEntity {

    @Id
    private String id;

    @NotNull
    private String name;

    @NotNull
    private String pw;

    @NotNull
    private String email;

    @NotNull
    private String subject;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "grade", column = @Column(table = "student_of_info")),
            @AttributeOverride(name = "room", column = @Column(table = "student_of_info")),
            @AttributeOverride(name = "number", column = @Column(table = "student_of_info"))
    })
    private StudentInfoJpaVO studentInfo;
}
