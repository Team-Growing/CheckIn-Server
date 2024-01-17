package dgsw.pioneers.checkIn.domain.absence.adapter.in.web.dto.res;

import dgsw.pioneers.checkIn.domain.absence.application.domain.model.Absence;
import dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.res.MemberInfoRes;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AbsenceWithMyInfoRes {

    private MemberInfoRes info;
    private List<Absence> absences;
}
