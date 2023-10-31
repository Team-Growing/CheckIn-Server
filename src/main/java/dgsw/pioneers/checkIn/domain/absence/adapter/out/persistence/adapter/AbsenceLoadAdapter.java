package dgsw.pioneers.checkIn.domain.absence.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.absence.adapter.out.persistence.AbsenceMapper;
import dgsw.pioneers.checkIn.domain.absence.adapter.out.persistence.AbsenceRepository;
import dgsw.pioneers.checkIn.domain.absence.adapter.out.persistence.aggregate.AbsenceJpaEntity;
import dgsw.pioneers.checkIn.domain.absence.application.domain.model.Absence;
import dgsw.pioneers.checkIn.domain.absence.application.domain.model.enums.AbsenceStatus;
import dgsw.pioneers.checkIn.domain.absence.application.port.out.LoadAbsencePort;
import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.Attendance;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
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
    public List<Absence> loadAbsences(LocalDate date) {
        return absenceRepository.findAllByCreatedAtOrderByCreatedAtDesc(date).stream()
                .map(absenceMapper::mapToDomainEntityWithMember).collect(Collectors.toList());
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

    @Override
    public List<Absence> loadAbsencesByLectureIdAndCreatedAtAndAbsenceStatus(Lecture.LectureId lectureId, AbsenceStatus absenceAllowed, LocalDate now) {
        return absenceRepository.findAllByLectureIdAndCreatedAtAndAbsenceStatus(lectureId.getValue(), absenceAllowed, now).stream()
                .map(absenceMapper::mapToDomainEntityWithMember).collect(Collectors.toList());
    }
}
