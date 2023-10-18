package dgsw.pioneers.checkIn.domain.lecture.application.domain.service;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture.LectureId;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.domain.lecture.application.port.in.LectureStatusUpdateUseCase;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.LoadLecturePort;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.UpdateLectureStatusPort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LectureStatusUpdateService implements LectureStatusUpdateUseCase {

    private final LoadLecturePort loadLecturePort;
    private final UpdateLectureStatusPort updateLectureStatusPort;

    @Override
    @Transactional
    public void updateStatus(List<LectureId> lectureIds, LectureStatus lectureStatus) {

        List<Lecture> lectures = lectureIds.stream().map(loadLecturePort::loadLectureWithWeekPlans).toList();

        lectures.forEach(lecture -> lecture.updateLectureStatus(lectureStatus));

        lectures.forEach(updateLectureStatusPort::updateLectureStatus);
    }
}
