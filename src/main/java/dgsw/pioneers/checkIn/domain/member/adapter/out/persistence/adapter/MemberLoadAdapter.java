package dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendant;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.MemberRepository;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.dao.MembersAsAttendantsDao;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.dao.MembersAsNonAttendantsDao;
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
    private final MembersAsAttendantsDao membersAsAttendantsDao;
    private final MembersAsNonAttendantsDao membersAsNonAttendantsDao;
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
    public List<Member> loadAttendants(Lecture.LectureId lectureId, AttendanceStatus attendanceStatus) {

        return membersAsAttendantsDao.findAllMembersWithAttendants(lectureId.getValue(), attendanceStatus)
                .stream().map(memberMapper::mapToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public List<Member> loadNonAttendantsByMember(Lecture.LectureId lectureId, List<Member> members) {

        return membersAsNonAttendantsDao.findAllMembersWithAttendants(
                        lectureId.getValue(), members.stream().map(member -> member.getMemberId().getValue()).collect(Collectors.toList()))
                .stream().map(memberMapper::mapToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public List<Member.MemberId> loadNonAttendantsByAttendant(Lecture.LectureId lectureId, List<Attendant> attendants) {

        return membersAsNonAttendantsDao.findAllMembersWithAttendants(
                        lectureId.getValue(), attendants.stream().map(attendant -> attendant.getAttendantId().getValue()).collect(Collectors.toList()))
                .stream().map(memberJpaEntity -> new Member.MemberId(memberJpaEntity.getId())).collect(Collectors.toList());
    }

    @Override
    public boolean existByMemberId(Member.MemberId memberId) {

        return memberRepository.existsById(memberId.getValue());
    }

    public MemberJpaEntity loadMemberJpaEntity(String memberId) {
        return memberRepository.findById(memberId).get();
    }
}
