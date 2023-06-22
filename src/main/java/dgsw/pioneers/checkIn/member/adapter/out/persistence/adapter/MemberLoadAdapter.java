package dgsw.pioneers.checkIn.member.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.member.global.annotation.PersistenceAdapter;
import dgsw.pioneers.checkIn.member.global.exception.custom.ResourceNotFoundException;
import dgsw.pioneers.checkIn.member.adapter.out.persistence.MemberMapper;
import dgsw.pioneers.checkIn.member.adapter.out.persistence.MemberRepository;
import dgsw.pioneers.checkIn.member.adapter.out.persistence.aggregate.MemberJpaEntity;
import dgsw.pioneers.checkIn.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.member.application.domain.model.Member.MemberId;
import dgsw.pioneers.checkIn.member.application.port.out.ExistMemberPort;
import dgsw.pioneers.checkIn.member.application.port.out.LoadMemberPort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MemberLoadAdapter implements LoadMemberPort, ExistMemberPort {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public Member loadMember(MemberId memberId) {

        MemberJpaEntity member = memberRepository.findById(memberId.getValue())
                .orElseThrow(ResourceNotFoundException::new);
        return memberMapper.mapToDomainEntity(member);
    }

    @Override
    public boolean existByMemberId(MemberId memberId) {
        return memberRepository.existsById(memberId.getValue());
    }
}
