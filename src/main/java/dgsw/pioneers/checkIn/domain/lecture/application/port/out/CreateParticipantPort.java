package dgsw.pioneers.checkIn.domain.lecture.application.port.out;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Participant;

public interface CreateParticipantPort {

    void createParticipant(Lecture lecture, Participant participant);
}
