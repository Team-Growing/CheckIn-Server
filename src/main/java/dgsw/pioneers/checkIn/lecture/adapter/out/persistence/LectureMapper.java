package dgsw.pioneers.checkIn.lecture.adapter.out.persistence;

import dgsw.pioneers.checkIn.lecture.adapter.out.persistence.aggregate.vo.AcceptableStudentJpaVO;
import dgsw.pioneers.checkIn.lecture.adapter.out.persistence.aggregate.LectureJpaEntity;
import dgsw.pioneers.checkIn.lecture.adapter.out.persistence.aggregate.vo.LectureScheduleJpaVO;
import dgsw.pioneers.checkIn.lecture.application.domain.model.AcceptableStudent;
import dgsw.pioneers.checkIn.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.lecture.application.domain.model.LectureSchedule;
import dgsw.pioneers.checkIn.lecture.application.domain.model.LectureTeacher;
import dgsw.pioneers.checkIn.member.application.domain.model.Member;
import org.springframework.stereotype.Component;

@Component
public class LectureMapper {

     public Lecture mapToDomainEntity(LectureJpaEntity lectureJpa) {
        return Lecture.withId(
                new Lecture.LectureId(lectureJpa.getId()),
                lectureJpa.getExplanation(),
                lectureJpa.getLectureStatus(),
                lectureJpa.getPlaceType(),
                AcceptableStudent.builder()
                        .maxStudent(lectureJpa.getAcceptableStudent().getMaxStudent())
                        .minStudent(lectureJpa.getAcceptableStudent().getMinStudent())
                        .targetGrade(lectureJpa.getAcceptableStudent().getTargetGrade()).build(),
                LectureTeacher.builder()
                        .memberId(new Member.MemberId(lectureJpa.getTeacherId())).build(),
                LectureSchedule.builder()
                        .startDay(lectureJpa.getLectureSchedule().getStartDay())
                        .endDay(lectureJpa.getLectureSchedule().getEndDay())
                        .dayOfWeek(lectureJpa.getLectureSchedule().getDayOfWeek())
                        .startTime(lectureJpa.getLectureSchedule().getStartTime())
                        .endTime(lectureJpa.getLectureSchedule().getEndTime()).build(),

        );
    }

    public LectureJpaEntity mapToJpaEntity(Lecture lecture) {
        return LectureJpaEntity.builder()
                .explanation(lecture.getExplanation())
                .lectureStatus(lecture.getLectureStatus())
                .placeType(lecture.getPlaceType())
                .teacherId(lecture.getLectureTeacher().getMemberId().getValue())
                .acceptableStudent(new AcceptableStudentJpaVO(
                        lecture.getAcceptableStudent().getMaxStudent(),
                        lecture.getAcceptableStudent().getMinStudent(),
                        lecture.getAcceptableStudent().getTargetGrade()
                ))
                .lectureSchedule(new LectureScheduleJpaVO(
                        lecture.getLectureSchedule().getStartDay(),
                        lecture.getLectureSchedule().getEndDay(),
                        lecture.getLectureSchedule().getDayOfWeek(),
                        lecture.getLectureSchedule().getStartTime(),
                        lecture.getLectureSchedule().getEndTime()
                ))
                .build();
    }
}
