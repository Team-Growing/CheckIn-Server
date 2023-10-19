package dgsw.pioneers.checkIn.domain.absence.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.absence.adapter.out.persistence.AbsenceMapper;
import dgsw.pioneers.checkIn.domain.absence.adapter.out.persistence.AbsenceRepository;
import dgsw.pioneers.checkIn.domain.absence.adapter.out.persistence.aggregate.AbsenceJpaEntity;
import dgsw.pioneers.checkIn.domain.absence.application.domain.model.Absence;
import dgsw.pioneers.checkIn.domain.absence.application.port.out.CreateAbsencePort;
import dgsw.pioneers.checkIn.domain.absence.application.port.out.UpdateAbsenceStatusPort;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.adapter.MemberLoadAdapter;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.aggregate.MemberJpaEntity;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class AbsencePersistenceAdapter implements CreateAbsencePort, UpdateAbsenceStatusPort {

    private final AbsenceMapper absenceMapper;
    private final AbsenceRepository absenceRepository;
    private final MemberLoadAdapter memberLoadAdapter;

    @Override
    public void createAbsence(Absence absence) {

        MemberJpaEntity memberJpaEntity = memberLoadAdapter.loadMemberJpaEntity(absence.getAbsentee().getMemberId().getValue());
        absenceRepository.save(absenceMapper.mapToJpaEntity(absence, memberJpaEntity));
    }

    @Override
    public void updateAbsenceStatus(Absence absence) {

        AbsenceJpaEntity absenceJpaEntity = absenceRepository.selectOne(absence.getAbsenceId().getValue()).get();
        absenceJpaEntity.updateAbsenceStatus(absence.getAbsenceStatus());
    }
}
