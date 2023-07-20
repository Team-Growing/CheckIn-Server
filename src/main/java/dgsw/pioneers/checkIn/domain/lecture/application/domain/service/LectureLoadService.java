package dgsw.pioneers.checkIn.domain.lecture.application.domain.service;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.port.in.LectureLoadUseCase;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.LoadLecturePort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LectureLoadService implements LectureLoadUseCase {

    private final LoadLecturePort loadLecturePort;

    @Override
    public Lecture loadLecture(Lecture.LectureId lectureId) {
        return loadLecturePort.loadLecture(lectureId.getValue());
    }

    @Override
    public List<Lecture> loadAllLecture() {
        return null;
    }
}
