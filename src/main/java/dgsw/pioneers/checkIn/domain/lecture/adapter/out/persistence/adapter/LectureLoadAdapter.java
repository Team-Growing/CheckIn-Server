package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import dgsw.pioneers.checkIn.global.exception.custom.ResourceNotFoundException;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.LectureMapper;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.LectureRepository;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.LectureJpaEntity;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.LoadLecturePort;
import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class LectureLoadAdapter implements LoadLecturePort {

    private final LectureRepository lectureRepository;
    private final LectureMapper lectureMapper;

    @Override
    public Lecture loadLectureWithWeekPlans(Lecture.LectureId lectureId) {

        LectureJpaEntity lectureJpaEntity = lectureRepository.findById(lectureId.getValue())
                .orElseThrow(ResourceNotFoundException::new);
        return lectureMapper.mapToDomainEntityWithWeekPlans(lectureJpaEntity);
    }

    @Override
    public Lecture loadLectureWithParticipants(Lecture.LectureId lectureId) {

        LectureJpaEntity lectureJpaEntity = lectureRepository.findByIdWithParticipants(lectureId.getValue())
                .orElseThrow(ResourceNotFoundException::new);
        return lectureMapper.mapToDomainEntityWithParticipants(lectureJpaEntity);
    }

    @Override
    public List<Lecture> loadAllLectureByStatus(LectureStatus lectureStatus) {

        return lectureRepository.findAllByLectureStatus(lectureStatus).stream()
                .map(lectureMapper::mapToDomainEntityWithWeekPlans).collect(Collectors.toList());
    }

    @Override
    public List<Lecture> loadAllLectureByDayOfWeek(LectureStatus lectureStatus, DayOfWeek dayOfWeek) {

        return lectureRepository.findAllByLectureStatusAndLectureScheduleDayOfWeek(lectureStatus, dayOfWeek).stream()
                .map(lectureMapper::mapToDomainEntityWithWeekPlans).collect(Collectors.toList());
    }

    public LectureJpaEntity loadLectureJpaEntity(Long lectureId) {

        return lectureRepository.findById(lectureId)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
