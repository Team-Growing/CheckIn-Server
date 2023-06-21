package dgsw.pioneers.checkIn.member.adapter.out.persistence.entity;

import dgsw.pioneers.checkIn.member.application.domain.model.enums.MemberRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberJpaEntity {

    @Id
    private String Id;

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
