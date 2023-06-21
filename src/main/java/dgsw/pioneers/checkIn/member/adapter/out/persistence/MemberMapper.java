package dgsw.pioneers.checkIn.member.adapter.out.persistence;

import dgsw.pioneers.checkIn.member.adapter.out.persistence.entity.MemberJpaEntity;
import dgsw.pioneers.checkIn.member.application.domain.model.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

     public Member mapToDomainEntity(MemberJpaEntity member) {
        return Member.withId(
                new Member.MemberId(member.getId()),
                member.getName(),
                member.getPw(),
                member.getEmail(),
                member.getMemberRole()
        );
    }

    public MemberJpaEntity mapToJpaEntity(Member member) {
        return MemberJpaEntity.builder()
                .id(member.getMemberId().getValue())
                .name(member.getName())
                .pw(member.getPw())
                .email(member.getEmail())
                .memberRole(member.getMemberRole())
                .build();
    }
}
