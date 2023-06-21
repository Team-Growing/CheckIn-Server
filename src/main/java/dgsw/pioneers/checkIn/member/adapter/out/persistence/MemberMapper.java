package dgsw.pioneers.checkIn.member.adapter.out.persistence;

import dgsw.pioneers.checkIn.member.adapter.out.persistence.entity.MemberJpaEntity;
import dgsw.pioneers.checkIn.member.application.domain.model.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    protected Member mapToDomainEntity(MemberJpaEntity member) {
        return Member.withId(
                new Member.MemberId(member.getId()),
                member.getName(),
                member.getPw(),
                member.getEmail(),
                member.getMemberRole()
        );
    }
}
