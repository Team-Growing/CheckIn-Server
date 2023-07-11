package dgsw.pioneers.checkIn.lecture.application.domain.service;

import dgsw.pioneers.checkIn.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.lecture.application.port.in.LectureGenerateCommand;
import dgsw.pioneers.checkIn.lecture.application.port.in.LectureGenerateUseCase;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import dgsw.pioneers.checkIn.lecture.application.port.out.CreateLecturePort;
import dgsw.pioneers.checkIn.member.application.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LectureGenerateService implements LectureGenerateUseCase {

    private final CreateLecturePort createLecturePort;

    @Override
    @Transactional
    public void generateLecture(Member.MemberId teacherId, LectureGenerateCommand signInCommand) {

        Lecture lecture = signInCommand.mapToDomainEntity(teacherId);

        createLecturePort.createLecture(lecture);
    }
}
