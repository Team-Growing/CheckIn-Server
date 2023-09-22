package dgsw.pioneers.checkIn.domain.lecture.application.domain.service;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.domain.lecture.application.port.in.LectureStatusUpdateUseCase;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.LoadLecturePort;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.UpdateLectureStatusPort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LectureStatusUpdateService implements LectureStatusUpdateUseCase {

    private final LoadLecturePort loadLecturePort;
    private final UpdateLectureStatusPort updateLectureStatusPort;

    @Override
    @Transactional
    public void updateStatus(Lecture.LectureId lectureId, LectureStatus lectureStatus) {

        Lecture lecture = loadLecturePort.loadLectureWithWeekPlans(lectureId);
        lecture.updateLectureStatus(lectureStatus);

        updateLectureStatusPort.updateLectureStatus(lecture);
    }
}
