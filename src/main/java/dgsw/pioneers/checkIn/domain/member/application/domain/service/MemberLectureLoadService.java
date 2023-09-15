package dgsw.pioneers.checkIn.domain.member.application.domain.service;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.LoadLectureByParticipantPort;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.port.in.MemberLectureLoadUseCase;
import dgsw.pioneers.checkIn.domain.member.application.port.out.LoadMemberPort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import dgsw.pioneers.checkIn.global.lib.zonedatetime.ZoneDateTimeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.List;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberLectureLoadService implements MemberLectureLoadUseCase {

    private final LoadLectureByParticipantPort loadLectureByParticipantPort;
    private final LoadMemberPort loadMemberPort;

    @Override
    public List<Lecture> loadLectureByMember(Member.MemberId memberId) {

        List<Lecture> lectures = loadLectureByParticipantPort.loadAllLectureByMemberAndStatus(memberId, LectureStatus.COURSE_PERIOD);
        lectures.forEach(this::updateTeacherInfo);

        return lectures;
    }

    @Override
    public List<Lecture> loadTodayLectureByMember(Member.MemberId memberId) {

        DayOfWeek dayOfWeek = ZoneDateTimeUtil.nowToLocalDate().getDayOfWeek();

        List<Lecture> lectures = loadLectureByParticipantPort.loadAllLectureByMemberAndStatusAndDayOfWeek(memberId, LectureStatus.COURSE_PERIOD, dayOfWeek);
        lectures.forEach(this::updateTeacherInfo);

        return lectures;
    }

    private void updateTeacherInfo(Lecture lecture) {

        Member teacher = loadMemberPort.loadMember(lecture.getLectureTeacher().getTeacherId());
        lecture.updateTeacherInfo(teacher.getName());
    }
}
