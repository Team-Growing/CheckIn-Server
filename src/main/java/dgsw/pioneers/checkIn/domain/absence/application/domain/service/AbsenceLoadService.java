package dgsw.pioneers.checkIn.domain.absence.application.domain.service;

import dgsw.pioneers.checkIn.domain.absence.application.domain.model.Absence;
import dgsw.pioneers.checkIn.domain.absence.application.port.in.AbsenceLoadUseCase;
import dgsw.pioneers.checkIn.domain.absence.application.port.out.LoadAbsencePort;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import dgsw.pioneers.checkIn.global.lib.zonedatetime.ZoneDateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AbsenceLoadService implements AbsenceLoadUseCase {

    private final LoadAbsencePort loadAbsencePort;

    @Override
    public List<Absence> loadMyAbsence(Member.MemberId memberId) {
        return loadAbsencePort.loadAbsencesByMemberIdAndCreatedAt(memberId, ZoneDateTimeUtil.nowToLocalDate());
    }
}
