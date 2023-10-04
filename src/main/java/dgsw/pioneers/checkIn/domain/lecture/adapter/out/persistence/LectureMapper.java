package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence;

import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.relation.LectureToMemberEntity;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo.DayOfWeekVO;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.*;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.WeekPlanJpaEntity;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo.AcceptableStudentJpaVO;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.LectureJpaEntity;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.vo.LectureScheduleJpaVO;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.global.annotation.Mapper;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public class LectureMapper {

    public Lecture mapToDomainEntity(LectureJpaEntity lectureJpa) {
        return Lecture.withId(
                new Lecture.LectureId(lectureJpa.getId()),
                lectureJpa.getLectureName(),
                lectureJpa.getExplanation(),
                lectureJpa.getLectureStatus(),
                lectureJpa.getPlaceType(),
                lectureJpa.getLectureTag(),
                AcceptableStudent.builder()
                        .maxStudent(lectureJpa.getAcceptableStudent().getMaxStudent())
                        .minStudent(lectureJpa.getAcceptableStudent().getMinStudent())
                        .targetGrade(lectureJpa.getAcceptableStudent().getTargetGrade()).build(),
                LectureTeacher.builder()
                        .teacherId(new Member.MemberId(lectureJpa.getTeacherId())).build(),
                LectureSchedule.builder()
                        .startDay(lectureJpa.getLectureSchedule().getStartDay())
                        .endDay(lectureJpa.getLectureSchedule().getEndDay())
                        .dayOfWeek(getDayOfWeek(lectureJpa.getDayOfWeekVO()))
                        .startTime(lectureJpa.getLectureSchedule().getStartTime())
                        .endTime(lectureJpa.getLectureSchedule().getEndTime()).build(),
                lectureJpa.getEnrollStudent(),
                null,
                null);
    }

     public Lecture mapToDomainEntityWithWeekPlans(LectureJpaEntity lectureJpa) {
         return Lecture.withId(
                new Lecture.LectureId(lectureJpa.getId()),
                lectureJpa.getLectureName(),
                lectureJpa.getExplanation(),
                lectureJpa.getLectureStatus(),
                lectureJpa.getPlaceType(),
                lectureJpa.getLectureTag(),
                AcceptableStudent.builder()
                        .maxStudent(lectureJpa.getAcceptableStudent().getMaxStudent())
                        .minStudent(lectureJpa.getAcceptableStudent().getMinStudent())
                        .targetGrade(lectureJpa.getAcceptableStudent().getTargetGrade()).build(),
                LectureTeacher.builder()
                        .teacherId(new Member.MemberId(lectureJpa.getTeacherId())).build(),
                LectureSchedule.builder()
                        .startDay(lectureJpa.getLectureSchedule().getStartDay())
                        .endDay(lectureJpa.getLectureSchedule().getEndDay())
                        .dayOfWeek(getDayOfWeek(lectureJpa.getDayOfWeekVO()))
                        .startTime(lectureJpa.getLectureSchedule().getStartTime())
                        .endTime(lectureJpa.getLectureSchedule().getEndTime()).build(),
                lectureJpa.getEnrollStudent(),
                getWeekPlans(lectureJpa),
                 null);
    }

    public Lecture mapToDomainEntityWithParticipants(LectureJpaEntity lectureJpa) {
        return Lecture.withId(
                new Lecture.LectureId(lectureJpa.getId()),
                lectureJpa.getLectureName(),
                lectureJpa.getExplanation(),
                lectureJpa.getLectureStatus(),
                lectureJpa.getPlaceType(),
                lectureJpa.getLectureTag(),
                AcceptableStudent.builder()
                        .maxStudent(lectureJpa.getAcceptableStudent().getMaxStudent())
                        .minStudent(lectureJpa.getAcceptableStudent().getMinStudent())
                        .targetGrade(lectureJpa.getAcceptableStudent().getTargetGrade()).build(),
                LectureTeacher.builder()
                        .teacherId(new Member.MemberId(lectureJpa.getTeacherId())).build(),
                LectureSchedule.builder()
                        .startDay(lectureJpa.getLectureSchedule().getStartDay())
                        .endDay(lectureJpa.getLectureSchedule().getEndDay())
                        .dayOfWeek(getDayOfWeek(lectureJpa.getDayOfWeekVO()))
                        .startTime(lectureJpa.getLectureSchedule().getStartTime())
                        .endTime(lectureJpa.getLectureSchedule().getEndTime()).build(),
                lectureJpa.getEnrollStudent(),
                null,
                getParticipants(lectureJpa));
    }

    public LectureJpaEntity mapToJpaEntity(Lecture lecture) {
        return LectureJpaEntity.builder()
                .lectureName(lecture.getLectureName())
                .explanation(lecture.getExplanation())
                .lectureStatus(lecture.getLectureStatus())
                .placeType(lecture.getPlaceType())
                .lectureTag(lecture.getLectureTag())
                .teacherId(lecture.getLectureTeacher().getTeacherId().getValue())
                .acceptableStudent(new AcceptableStudentJpaVO(
                        lecture.getAcceptableStudent().getMaxStudent(),
                        lecture.getAcceptableStudent().getMinStudent(),
                        lecture.getAcceptableStudent().getTargetGrade()
                ))
                .lectureSchedule(new LectureScheduleJpaVO(
                        lecture.getLectureSchedule().getStartDay(),
                        lecture.getLectureSchedule().getEndDay(),
                        lecture.getLectureSchedule().getStartTime(),
                        lecture.getLectureSchedule().getEndTime()
                ))
                .dayOfWeekVO(getDayOfWeekVO(lecture.getLectureSchedule().getDayOfWeek()))
                .enrollStudent(lecture.getEnrollStudent())
                .build();
    }

    private Set<DayOfWeekVO> getDayOfWeekVO(Set<DayOfWeek> dayOfWeeks) {
        return dayOfWeeks.stream().map(DayOfWeekVO::new).collect(Collectors.toSet());
    }

    private Set<DayOfWeek> getDayOfWeek(Set<DayOfWeekVO> dayOfWeeks) {
        return dayOfWeeks.stream().map(DayOfWeekVO::getDayOfWeek).collect(Collectors.toSet());
    }

    public List<Participant> getParticipants(LectureJpaEntity lectureJpa) {
        if (lectureJpa.getParticipants() == null) return null;
        else return lectureJpa.getParticipants().stream().map(this::participantMapToDomain).collect(Collectors.toList());
    }

    private Participant participantMapToDomain(LectureToMemberEntity lectureToMember) {
        return Participant.builder()
                .participantId(new Member.MemberId(lectureToMember.getMemberJpaEntity().getId()))
                .applyDateTime(lectureToMember.getApplyDateTime())
                .build();
    }

    private List<WeekPlan> getWeekPlans(LectureJpaEntity lectureJpa) {
         if (lectureJpa.getWeekPlans() == null) return null;
         else return lectureJpa.getWeekPlans().stream().map(this::weekPlanMapToDomain).collect(Collectors.toList());
    }

    private WeekPlan weekPlanMapToDomain(WeekPlanJpaEntity weekPlanJpaEntity) {
        return WeekPlan.builder()
                .week(weekPlanJpaEntity.getWeek())
                .introduction(weekPlanJpaEntity.getIntroduction())
                .build();
    }

    public List<WeekPlanJpaEntity> weekPlanMapToJpa(LectureJpaEntity lectureJpaEntity, List<WeekPlan> weekPlans) {
        return weekPlans.stream().map(weekPlan ->
                WeekPlanJpaEntity.builder()
                        .week(weekPlan.getWeek())
                        .introduction(weekPlan.getIntroduction())
                        .lectureJpaEntity(lectureJpaEntity)
                        .build()
                ).collect(Collectors.toList());
    }
}
