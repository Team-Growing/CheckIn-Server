package dgsw.pioneers.checkIn.member.adapter.out.persistence;

import dgsw.pioneers.checkIn.member.adapter.out.persistence.aggregate.MemberJpaEntity;
import dgsw.pioneers.checkIn.member.adapter.out.persistence.aggregate.StudentInfoJpaVO;
import dgsw.pioneers.checkIn.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.member.application.domain.model.StudentInfo;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

     public Member mapToDomainEntity(MemberJpaEntity member) {
        return Member.withId(
                new Member.MemberId(member.getId()),
                member.getName(),
                member.getPw(),
                member.getEmail(),
                member.getMemberRole(),
                StudentInfo.builder()
                        .year(member.getStudentInfo().getYear())
                        .grade(member.getStudentInfo().getGrade())
                        .room(member.getStudentInfo().getRoom())
                        .number(member.getStudentInfo().getNumber())
                        .build()
        );
    }

    public MemberJpaEntity mapToJpaEntity(Member member) {
        return MemberJpaEntity.builder()
                .id(member.getMemberId().getValue())
                .name(member.getName())
                .pw(member.getPw())
                .email(member.getEmail())
                .memberRole(member.getMemberRole())
                .studentInfo(StudentInfoJpaVO.builder()
                        .year(member.getStudentInfo().getYear())
                        .grade(member.getStudentInfo().getGrade())
                        .room(member.getStudentInfo().getRoom())
                        .number(member.getStudentInfo().getNumber())
                        .build())
                .build();
    }
}
