package dgsw.pioneers.checkIn.domain.attendance.adapter.in.web.dto.res;

import dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.StudentInfoDto;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberInfoToAttendanceRes {

    private String id;
    private String name;
    private StudentInfoDto studentInfo;

    public static MemberInfoToAttendanceRes convertToDTO(Member member) {
        return new MemberInfoToAttendanceRes(
                member.getMemberId().getValue(),
                member.getName(),
                StudentInfoDto.convertToDTO(member.getStudentInfo()));
    }
}
