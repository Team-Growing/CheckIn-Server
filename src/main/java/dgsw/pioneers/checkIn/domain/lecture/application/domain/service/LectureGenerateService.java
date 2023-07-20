package dgsw.pioneers.checkIn.domain.lecture.application.domain.service;

import dgsw.pioneers.checkIn.domain.lecture.adapter.in.web.dto.req.LectureGenerateReq;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.port.in.LectureGenerateUseCase;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.CreateLecturePort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LectureGenerateService implements LectureGenerateUseCase {

    private final CreateLecturePort createLecturePort;

    @Override
    @Transactional
    public void generateLecture(Member.MemberId teacherId, LectureGenerateReq lectureGenerateReq) {

        Lecture lecture = lectureGenerateReq.mapToDomainEntity(teacherId);

        createLecturePort.createLecture(lecture);
    }
}
