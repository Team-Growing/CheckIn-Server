package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import dgsw.pioneers.checkIn.global.exception.custom.ResourceNotFoundException;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.LectureMapper;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.LectureRepository;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.LectureJpaEntity;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.LoadLecturePort;
import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class LectureLoadAdapter implements LoadLecturePort {

    private final LectureRepository lectureRepository;
    private final LectureMapper lectureMapper;

    @Override
    public boolean isLectureActive(Lecture.LectureId lectureId, LocalTime currentTime) {
        return lectureRepository.isLectureActive(lectureId.getValue(), currentTime);
    }

    @Override
    public Lecture loadLecture(Lecture.LectureId lectureId) {

        LectureJpaEntity lectureJpaEntity = lectureRepository.findById(lectureId.getValue())
                .orElseThrow(ResourceNotFoundException::new);
        return lectureMapper.mapToDomainEntity(lectureJpaEntity);
    }

    @Override
    public Lecture loadLectureWithWeekPlans(Lecture.LectureId lectureId) {

        LectureJpaEntity lectureJpaEntity = lectureRepository.findByIdWithWeekPlans(lectureId.getValue())
                .orElseThrow(ResourceNotFoundException::new);
        return lectureMapper.mapToDomainEntityWithWeekPlans(lectureJpaEntity);
    }

    @Override
    public Lecture loadLectureWithParticipants(Lecture.LectureId lectureId) {

        LectureJpaEntity lectureJpaEntity = lectureRepository.selectByIdWithParticipants(lectureId.getValue())
                .orElseThrow(ResourceNotFoundException::new);
        return lectureMapper.mapToDomainEntityWithParticipants(lectureJpaEntity);
    }

    @Override
    public List<Lecture> loadAllLectureByStatus(LectureStatus lectureStatus) {

        return lectureRepository.findAllByLectureStatus(lectureStatus).stream()
                .map(lectureMapper::mapToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public List<Lecture> loadAllLectureByStatusAndTargetGrade(LectureStatus lectureStatus, int targetGrade) {

        return lectureRepository.findAllByLectureStatusAndAcceptableStudent_TargetGrade(lectureStatus, targetGrade).stream()
                .map(lectureMapper::mapToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public List<Lecture> loadAllLectureByDayOfWeek(LectureStatus lectureStatus, DayOfWeek dayOfWeek) {

        return lectureRepository.findAllByLectureStatusAndDayOfWeekVO(lectureStatus, dayOfWeek).stream()
                .map(lectureMapper::mapToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public List<Lecture> loadAllLectureByLectureTeacherAndLectureStatus(Member.MemberId memberId, LectureStatus lectureStatus) {

        return lectureRepository.findAllByTeacherIdAndLectureStatus(memberId.getValue(), lectureStatus).stream()
                .map(lectureMapper::mapToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public List<Lecture> loadAllLectureByLectureTeacherAndLectureStatusAndDayOfWeek(Member.MemberId memberId, LectureStatus lectureStatus, DayOfWeek dayOfWeek) {

        return lectureRepository.findAllByTeacherIdAndLectureStatusAndDayOfWeekVO(memberId.getValue(), lectureStatus, dayOfWeek).stream()
                .map(lectureMapper::mapToDomainEntity).collect(Collectors.toList());
    }

    public LectureJpaEntity loadLectureJpaEntity(Long lectureId) {

        return lectureRepository.findById(lectureId)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
