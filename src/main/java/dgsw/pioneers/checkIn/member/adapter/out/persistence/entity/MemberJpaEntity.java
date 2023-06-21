package dgsw.pioneers.checkIn.member.adapter.out.persistence.entity;

import dgsw.pioneers.checkIn.member.application.domain.model.MemberRole;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@Builder
@Table(name = "member")
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
    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;
}
