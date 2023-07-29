package dgsw.pioneers.checkIn.domain.lecture.application.domain.service;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.port.in.LectureGenerateUseCase;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.CreateLecturePort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LectureGenerateService implements LectureGenerateUseCase {

    private final CreateLecturePort createLecturePort;

    @Override
    @Transactional
    public void generateLecture(Lecture lecture) {
        createLecturePort.createLecture(lecture);
    }
}
