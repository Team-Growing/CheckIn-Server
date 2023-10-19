package dgsw.pioneers.checkIn.domain.absence.application.port.in;

import dgsw.pioneers.checkIn.domain.absence.application.domain.model.Absence;

public interface AbsenceStatusUpdateUseCase {

    void allow(Absence.AbsenceId absenceId);
    void deny(Absence.AbsenceId absenceId);
}
