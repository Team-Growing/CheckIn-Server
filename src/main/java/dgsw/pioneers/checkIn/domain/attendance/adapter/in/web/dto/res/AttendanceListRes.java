package dgsw.pioneers.checkIn.domain.attendance.adapter.in.web.dto.res;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class AttendanceListRes {

    private int attendStudentCnt;
    private List<MemberInfoToAttendanceRes> attendants;
    private List<MemberInfoToAttendanceRes> nonAttendants;

    public static AttendanceListRes convertToDTO(List<Member> attendants, List<Member> nonAttendants) {
        return AttendanceListRes.builder()
                .attendStudentCnt(attendants.size())
                .attendants(attendants.stream()
                        .map(MemberInfoToAttendanceRes::convertToDTO).collect(Collectors.toList()))
                .nonAttendants(nonAttendants.stream()
                        .map(MemberInfoToAttendanceRes::convertToDTO).collect(Collectors.toList()))
                .build();
    }
}