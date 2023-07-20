package dgsw.pioneers.checkIn.lecture.application.port.in;

import dgsw.pioneers.checkIn.lecture.adapter.in.web.dto.req.LectureGenerateReq;
import dgsw.pioneers.checkIn.member.application.domain.model.Member;

public interface LectureGenerateUseCase {
    void generateLecture(Member.MemberId teacherId, LectureGenerateReq lectureGenerateReq);
}
