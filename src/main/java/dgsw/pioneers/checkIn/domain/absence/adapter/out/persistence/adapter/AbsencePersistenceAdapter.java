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

        //이떄 selectOne(직접 JPQL로 정의한 쿼리)를 쓴다면 select query가 한번 더 나가게 된다.
        //추축하건대 쿼리메서드를 쓰면 1차캐시에서 조회하는 거 같고, @Query를 쓰면 직접 DB에 질의하는 듯
        //그러므로 처음에 조회할때는 JPQL을 쓰고 다음에 다시 그 Entity가 필요하다면 커리 메서드로 조회하면 추가 쿼리가 나가지 않음
        AbsenceJpaEntity absenceJpaEntity = absenceRepository.findById(absence.getAbsenceId().getValue()).get();
        absenceJpaEntity.updateAbsenceStatus(absence.getAbsenceStatus());
    }
}
