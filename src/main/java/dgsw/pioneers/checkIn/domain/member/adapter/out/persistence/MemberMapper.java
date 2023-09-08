package dgsw.pioneers.checkIn.domain.member.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.vo.StudentInfoJpaVO;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.StudentInfo;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.MemberJpaEntity;
import dgsw.pioneers.checkIn.global.annotation.Mapper;

import java.util.Optional;

@Mapper
public class MemberMapper {

     public Member mapToDomainEntity(MemberJpaEntity member) {
        return Member.withId(
                new Member.MemberId(member.getId()),
                member.getName(),
                member.getPw(),
                member.getEmail(),
                member.getMemberRole(),
                getStudentInfo(member.getStudentInfo()),
                member.getSubject()
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
                .subject(member.getSubject())
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
