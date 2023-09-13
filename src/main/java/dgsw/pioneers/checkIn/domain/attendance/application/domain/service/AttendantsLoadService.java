package dgsw.pioneers.checkIn.domain.attendance.application.domain.service;

import dgsw.pioneers.checkIn.domain.attendance.application.domain.model.enums.AttendanceStatus;
import dgsw.pioneers.checkIn.domain.attendance.application.port.in.AttendantsLoadUseCase;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.port.out.LoadMemberPort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AttendantsLoadService implements AttendantsLoadUseCase {

    private final LoadMemberPort loadMemberPort;

    @Override
    public List<Member> loadAttendants(Lecture.LectureId lectureId) {

        return loadMemberPort.loadAttendants(lectureId, AttendanceStatus.PERIOD_VALID);
    }

    @Override
    public List<Member> loadNonAttendants(Lecture.LectureId lectureId, List<Member> members) {

        return loadMemberPort.loadNonAttendantsByMember(lectureId, members);
    }
}
