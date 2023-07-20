package dgsw.pioneers.checkIn.lecture.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import dgsw.pioneers.checkIn.lecture.adapter.out.persistence.LectureMapper;
import dgsw.pioneers.checkIn.lecture.adapter.out.persistence.LectureRepository;
import dgsw.pioneers.checkIn.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.lecture.application.port.out.PersistenceLecturePort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class LecturePersistenceAdapter implements PersistenceLecturePort {

    private final LectureRepository lectureRepository;
    private final LectureMapper lectureMapper;

    @Override
    public void persistenceLecture(Lecture lecture) {

        lectureRepository.save(lectureMapper.mapToJpaEntity(lecture));
    }
}
