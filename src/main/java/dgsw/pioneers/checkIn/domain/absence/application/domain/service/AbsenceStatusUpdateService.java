package dgsw.pioneers.checkIn.domain.absence.application.domain.service;

import dgsw.pioneers.checkIn.domain.absence.application.domain.model.Absence;
import dgsw.pioneers.checkIn.domain.absence.application.domain.model.enums.AbsenceStatus;
import dgsw.pioneers.checkIn.domain.absence.application.port.in.AbsenceStatusUpdateUseCase;
import dgsw.pioneers.checkIn.domain.absence.application.port.out.LoadAbsencePort;
import dgsw.pioneers.checkIn.domain.absence.application.port.out.UpdateAbsenceStatusPort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AbsenceStatusUpdateService implements AbsenceStatusUpdateUseCase {

    private final LoadAbsencePort loadAbsencePort;
    private final UpdateAbsenceStatusPort updateAbsenceStatusPort;

    @Override
    @Transactional
    public void allow(Absence.AbsenceId absenceId) {

        Absence absence = loadAbsencePort.loadAbsenceById(absenceId);
        absence.updateAbsenceStatus(AbsenceStatus.ABSENCE_ALLOWED);

        updateAbsenceStatusPort.updateAbsenceStatus(absence);
    }

    @Override
    @Transactional
    public void deny(Absence.AbsenceId absenceId) {

        Absence absence = loadAbsencePort.loadAbsenceById(absenceId);
        absence.updateAbsenceStatus(AbsenceStatus.ABSENCE_DENIED);

        updateAbsenceStatusPort.updateAbsenceStatus(absence);
    }
}
