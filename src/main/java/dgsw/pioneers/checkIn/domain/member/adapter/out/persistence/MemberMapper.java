package dgsw.pioneers.checkIn.member.adapter.out.persistence;

import dgsw.pioneers.checkIn.member.adapter.out.persistence.aggregate.MemberJpaEntity;
import dgsw.pioneers.checkIn.member.adapter.out.persistence.aggregate.vo.StudentInfoJpaVO;
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
                getStudentInfo(member.getStudentInfo())
        );
    }

    public MemberJpaEntity mapToJpaEntity(Member member) {
        return MemberJpaEntity.builder()
                .id(member.getMemberId().getValue())
                .name(member.getName())
                .pw(member.getPw())
                .email(member.getEmail())
                .memberRole(member.getMemberRole())
                .studentInfo(getStudentJPAInfo(member.getStudentInfo()))
                .build();
    }

    private StudentInfoJpaVO getStudentJPAInfo(StudentInfo studentInfo) {

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

    private StudentInfo getStudentInfo(StudentInfoJpaVO studentInfoJpaVO) {

        if (Optional.ofNullable(studentInfoJpaVO).isEmpty()) {
            return null;
        } else {
            return StudentInfo.builder()
                    .year(studentInfoJpaVO.getYear())
                    .grade(studentInfoJpaVO.getGrade())
                    .room(studentInfoJpaVO.getRoom())
                    .number(studentInfoJpaVO.getNumber())
                    .build();
        }
    }
}
