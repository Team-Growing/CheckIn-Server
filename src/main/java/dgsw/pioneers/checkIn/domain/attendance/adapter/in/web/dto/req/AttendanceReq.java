package dgsw.pioneers.checkIn.domain.attendance.adapter.in.web.dto.req;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class AttendanceReq {

    @NotBlank
    private String memberId;
}
