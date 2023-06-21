package dgsw.pioneers.checkIn.member.adapter.out.persistence;

import dgsw.pioneers.checkIn.common.annotation.PersistenceAdapter;
import dgsw.pioneers.checkIn.member.adapter.out.persistence.entity.MemberJpaEntity;
import dgsw.pioneers.checkIn.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.member.application.port.out.LoadMemberPort;
import lombok.RequiredArgsConstructor;

import java.lang.module.ResolutionException;

@PersistenceAdapter
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements LoadMemberPort {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public Member loadMember(Member.MemberId memberId) {

        MemberJpaEntity member = memberRepository.findById(memberId.getValue())
                .orElseThrow(ResolutionException::new);

        return memberMapper.mapToDomainEntity(member);
    }
}
