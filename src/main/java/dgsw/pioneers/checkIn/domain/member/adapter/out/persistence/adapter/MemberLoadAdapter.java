package dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.MemberRepository;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole;
import dgsw.pioneers.checkIn.domain.member.application.port.out.ExistMemberPort;
import dgsw.pioneers.checkIn.domain.member.application.port.out.LoadMemberPort;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import dgsw.pioneers.checkIn.global.exception.custom.ResourceNotFoundException;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.MemberMapper;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.MemberJpaEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class MemberLoadAdapter implements LoadMemberPort, ExistMemberPort {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public Member loadMember(Member.MemberId memberId) {

        MemberJpaEntity member = memberRepository.findById(memberId.getValue())
                .orElseThrow(ResourceNotFoundException::new);
        return memberMapper.mapToDomainEntity(member);
    }

    @Override
    public List<Member> loadTeachers(MemberRole memberRole) {

        return memberRepository.findAllByMemberRoleOrderByName(memberRole)
                .stream().map(memberMapper::mapToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public boolean existByMemberId(Member.MemberId memberId) {
        return memberRepository.existsById(memberId.getValue());
    }

    public MemberJpaEntity loadMemberJpaEntity(String  memberId) {
        return memberRepository.findById(memberId).get();
    }
}
