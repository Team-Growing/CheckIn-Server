package dgsw.pioneers.checkIn.domain.lecture.application.domain.service;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Participant;
import dgsw.pioneers.checkIn.domain.lecture.application.port.in.EnrolmentUseCase;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.CreateParticipantPort;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.LoadLecturePort;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EnrolmentService implements EnrolmentUseCase {

    private final LoadLecturePort loadLecturePort;
    private final CreateParticipantPort createParticipantPort;

    @Override
    @Transactional
    public void lectureEnrolment(Lecture.LectureId lectureId, Member.MemberId studentId) {

        Lecture lecture = loadLecturePort.loadLecture(lectureId.getValue());
        Participant participant = lecture.registerParticipant(studentId);

        createParticipantPort.createParticipant(lectureId, participant);
    }
}
