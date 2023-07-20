package dgsw.pioneers.checkIn.domain.lecture.application.port.in;

import dgsw.pioneers.checkIn.domain.lecture.adapter.in.web.dto.req.LectureGenerateReq;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;

public interface LectureGenerateUseCase {
    void generateLecture(Member.MemberId teacherId, LectureGenerateReq lectureGenerateReq);
}
