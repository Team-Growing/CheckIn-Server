package dgsw.pioneers.checkIn.domain.absence.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.absence.adapter.out.persistence.AbsenceMapper;
import dgsw.pioneers.checkIn.domain.absence.adapter.out.persistence.AbsenceRepository;
import dgsw.pioneers.checkIn.domain.absence.adapter.out.persistence.aggregate.AbsenceJpaEntity;
import dgsw.pioneers.checkIn.domain.absence.application.domain.model.Absence;
import dgsw.pioneers.checkIn.domain.absence.application.port.out.LoadAbsencePort;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import dgsw.pioneers.checkIn.global.exception.custom.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class AbsenceLoadAdapter implements LoadAbsencePort {

    private final AbsenceRepository absenceRepository;
    private final AbsenceMapper absenceMapper;

    @Override
    public Absence loadAbsenceById(Absence.AbsenceId absenceId) {

        AbsenceJpaEntity absenceJpaEntity = absenceRepository.selectOne(absenceId.getValue())
                .orElseThrow(ResourceNotFoundException::new);

        return absenceMapper.mapToDomainEntity(absenceJpaEntity);
    }

    @Override
    public Absence loadAbsenceByIdWithMember(Absence.AbsenceId absenceId) {

        AbsenceJpaEntity absenceJpaEntity = absenceRepository.findById(absenceId.getValue())
                .orElseThrow(ResourceNotFoundException::new);

        return absenceMapper.mapToDomainEntityWithMember(absenceJpaEntity);
    }

    @Override
    public List<Absence> loadAbsences() {
        return absenceRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(absenceMapper::mapToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public List<Absence> loadAbsencesByMemberIdAndCreatedAt(Member.MemberId memberId, LocalDate now) {
        return absenceRepository.findAllByMemberJpaEntity_IdAndCreatedAt(memberId.getValue(), now).stream()
                .map(absenceMapper::mapToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public boolean existByAttendanceIdAndMemberId(Attendance.AttendanceId attendanceId, Member.MemberId memberId) {
        return absenceRepository.existsByAttendanceId_IdAndMemberJpaEntity_Id(attendanceId.getValue(), memberId.getValue());
    }
}
