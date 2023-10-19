package dgsw.pioneers.checkIn.domain.absence.application.port.in;

import dgsw.pioneers.checkIn.domain.absence.adapter.in.web.dto.req.AbsenceGenerateReq;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member.MemberId;

public interface AbsenceUseCase {

    void absence(AbsenceGenerateReq req, MemberId memberId);
}
