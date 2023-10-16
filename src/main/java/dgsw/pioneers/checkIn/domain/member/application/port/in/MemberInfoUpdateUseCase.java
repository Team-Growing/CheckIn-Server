package dgsw.pioneers.checkIn.domain.member.application.port.in;

import dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.req.UpdateStudentInfoReq;
import dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.req.UpdateTeacherInfoReq;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;

import javax.validation.Valid;

public interface MemberInfoUpdateUseCase {

    void updateTeacherInfo(Member member, @Valid UpdateTeacherInfoReq updateTeacherInfoReq);
    void updateStudentInfo(Member member, @Valid UpdateStudentInfoReq updateStudentInfoReq);
}
