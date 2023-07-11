package dgsw.pioneers.checkIn.lecture.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import dgsw.pioneers.checkIn.lecture.adapter.out.persistence.LectureMapper;
import dgsw.pioneers.checkIn.lecture.adapter.out.persistence.LectureRepository;
import dgsw.pioneers.checkIn.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.lecture.application.port.out.CreateLecturePort;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class LecturePersistenceAdapter implements CreateLecturePort {

    private final LectureRepository lectureRepository;
    private final LectureMapper lectureMapper;

    @Override
    public void createLecture(Lecture lecture) {

        lectureRepository.save(lectureMapper.mapToJpaEntity(lecture));
    }
}
