package dgsw.pioneers.checkIn.member.adapter.out.persistence;

import dgsw.pioneers.checkIn.member.adapter.out.persistence.aggregate.MemberJpaEntity;
import dgsw.pioneers.checkIn.member.adapter.out.persistence.aggregate.StudentInfoJpaVO;
import dgsw.pioneers.checkIn.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.member.application.domain.model.StudentInfo;
import org.springframework.stereotype.Component;

import java.util.Optional;

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
                .studentInfo(getStudentInfo(member.getStudentInfo()))
                .build();
    }

    private StudentInfoJpaVO getStudentInfo(StudentInfo studentInfo) {

         if (Optional.ofNullable(studentInfo).isEmpty()) {
             return null;
         } else {
             return StudentInfoJpaVO.builder()
                     .year(studentInfo.getYear())
                     .grade(studentInfo.getGrade())
                     .room(studentInfo.getRoom())
                     .number(studentInfo.getNumber())
                     .build();
         }
    }
}
