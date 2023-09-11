package dgsw.pioneers.checkIn.domain.lecture.application.domain.service;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.exception.LectureDuplicatedException;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.port.in.EnrolmentUseCase;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.CreateParticipantPort;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.LoadLecturePort;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.LoadParticipantPort;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EnrolmentService implements EnrolmentUseCase {

    private final LoadLecturePort loadLecturePort;
    private final LoadParticipantPort loadParticipantPort;
    private final CreateParticipantPort createParticipantPort;

    @Override
    @Transactional
    public void lectureEnrolment(Lecture.LectureId lectureId, Member student) {

        if (loadParticipantPort.existByLectureIdAndMemberId(lectureId, student.getMemberId())) {
            throw new LectureDuplicatedException();
        }

        Lecture lecture = loadLecturePort.loadLecture(lectureId);

        lecture.addParticipant(student);
        createParticipantPort.createParticipant(lecture);
    }
}
