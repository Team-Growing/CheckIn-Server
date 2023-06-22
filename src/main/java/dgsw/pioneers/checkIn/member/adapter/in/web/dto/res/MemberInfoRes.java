package dgsw.pioneers.checkIn.member.adapter.in.web.dto.res;

import dgsw.pioneers.checkIn.member.adapter.in.web.dto.StudentInfo;
import dgsw.pioneers.checkIn.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.member.application.domain.model.MemberRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberInfoRes {

    private String id;
    private String email;
    private String name;
    private MemberRole memberRole;
    private StudentInfo studentInfo;

    public static MemberInfoRes convertToDTO(Member member) {
        return MemberInfoRes.builder()
                .id(member.getMemberId().getValue())
                .email(member.getEmail())
                .name(member.getName())
                .memberRole(member.getMemberRole())
                .studentInfo(StudentInfo.builder()
                        .grade(member.getStudentInfo().getGrade())
                        .room(member.getStudentInfo().getRoom())
                        .number(member.getStudentInfo().getNumber()).build()
                ).build();
    }
}
