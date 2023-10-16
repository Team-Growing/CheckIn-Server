package dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.MemberJpaEntity;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.vo.StudentInfoJpaVO;
import dgsw.pioneers.checkIn.domain.member.application.port.out.UpdateMemberPort;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.MemberMapper;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.MemberRepository;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.port.out.CreateMemberPort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements CreateMemberPort, UpdateMemberPort {

    private final MemberRepository memberRepository;
    private final MemberLoadAdapter memberLoadAdapter;
    private final MemberMapper memberMapper;

    @Override
    public void createMember(Member member) {

        memberRepository.save(memberMapper.mapToJpaEntity(member));
    }

    @Override
    public void updateStudent(Member member) {

        MemberJpaEntity memberJpaEntity = memberLoadAdapter.loadMemberJpaEntity(member.getMemberId().getValue());

        memberJpaEntity.updateMemberInfo(
                member.getPw(),
                member.getEmail(),
                memberJpaEntity.getSubject()
        );

        StudentInfoJpaVO studentJPAInfo = memberMapper.getStudentJPAInfo(member.getStudentInfo());
        memberJpaEntity.updateStudentInfo(studentJPAInfo);
    }

    @Override
    public void updateTeacher(Member member) {

        MemberJpaEntity memberJpaEntity = memberLoadAdapter.loadMemberJpaEntity(member.getMemberId().getValue());

        memberJpaEntity.updateMemberInfo(
                member.getPw(),
                member.getEmail(),
                member.getSubject()
        );
    }
}
