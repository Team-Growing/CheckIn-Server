package dgsw.pioneers.checkIn.lecture.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import dgsw.pioneers.checkIn.global.exception.custom.ResourceNotFoundException;
import dgsw.pioneers.checkIn.lecture.adapter.out.persistence.LectureMapper;
import dgsw.pioneers.checkIn.lecture.adapter.out.persistence.LectureRepository;
import dgsw.pioneers.checkIn.lecture.adapter.out.persistence.aggregate.LectureJpaEntity;
import dgsw.pioneers.checkIn.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.lecture.application.port.out.LoadLecturePort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class lectureLoadAdapter implements LoadLecturePort {

    private final LectureRepository lectureRepository;
    private final LectureMapper lectureMapper;

    @Override
    public Lecture loadMember(Long lectureId) {

        LectureJpaEntity lectureJpaEntity = lectureRepository.findById(lectureId)
                .orElseThrow(ResourceNotFoundException::new);
        return lectureMapper.mapToDomainEntity(lectureJpaEntity);
    }
}