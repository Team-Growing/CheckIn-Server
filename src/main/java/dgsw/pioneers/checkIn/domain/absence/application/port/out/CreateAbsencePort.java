package dgsw.pioneers.checkIn.domain.absence.application.port.out;

import dgsw.pioneers.checkIn.domain.absence.application.domain.model.Absence;

public interface CreateAbsencePort {

    void createAbsence(Absence absence);
}
