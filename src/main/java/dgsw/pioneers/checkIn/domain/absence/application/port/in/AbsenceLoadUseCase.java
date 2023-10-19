package dgsw.pioneers.checkIn.domain.absence.application.port.in;

import dgsw.pioneers.checkIn.domain.absence.application.domain.model.Absence;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;

import java.util.List;

public interface AbsenceLoadUseCase {

    List<Absence> loadMyAbsence(Member.MemberId memberId);
}
