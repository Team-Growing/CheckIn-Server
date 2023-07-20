package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.LectureMapper;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.LectureRepository;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.LectureJpaEntity;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.WeekPlanJpaEntity;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.CreateLecturePort;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.UpdateLecturePort;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class LecturePersistenceAdapter implements CreateLecturePort, UpdateLecturePort {

    private final LectureRepository lectureRepository;
    private final LectureMapper lectureMapper;

    @Override
    public void createLecture(Lecture lecture) {
        lectureRepository.save(lectureMapper.mapToJpaEntity(lecture));
    }

    @Override
    public void updateLecture(Lecture lecture) {

        LectureJpaEntity lectureJpaEntity = lectureRepository.findById(lecture.getLectureId().getValue()).get();
        lectureJpaEntity.getWeekPlans().clear();

        List<WeekPlanJpaEntity> weekPlanJpaEntities = lectureMapper.weekPlanMapToJpa(lectureJpaEntity, lecture.getWeekPlans());
        weekPlanJpaEntities.forEach(lectureJpaEntity::addWeekPlans);

        lectureRepository.save(lectureJpaEntity);
    }
}
