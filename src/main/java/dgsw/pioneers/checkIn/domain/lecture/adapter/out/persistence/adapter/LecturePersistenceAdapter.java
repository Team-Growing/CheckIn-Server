package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.LectureMapper;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.LectureRepository;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.LectureToMemberRepository;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.LectureJpaEntity;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.WeekPlanJpaEntity;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.relation.LectureToMemberEntity;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Participant;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.CreateLecturePort;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.CreateParticipantPort;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.UpdateLectureWeekPlansPort;
import dgsw.pioneers.checkIn.domain.member.adapter.out.persistence.adapter.MemberLoadAdapter;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class LecturePersistenceAdapter implements CreateLecturePort, UpdateLectureWeekPlansPort, CreateParticipantPort {

    private final LectureRepository lectureRepository;
    private final LectureToMemberRepository lectureToMemberRepository;
    private final MemberLoadAdapter memberLoadAdapter;
    private final LectureMapper lectureMapper;

    @Override
    public void createLecture(Lecture lecture) {
        lectureRepository.save(lectureMapper.mapToJpaEntity(lecture));
    }

    @Override
    public void updateLectureWeekPlans(Lecture lecture) {

        LectureJpaEntity lectureJpaEntity = lectureRepository.findById(lecture.getLectureId().getValue()).get();
        lectureJpaEntity.getWeekPlans().clear();

        List<WeekPlanJpaEntity> weekPlanJpaEntities = lectureMapper.weekPlanMapToJpa(lectureJpaEntity, lecture.getWeekPlans());
        weekPlanJpaEntities.forEach(lectureJpaEntity::addWeekPlans);

        lectureRepository.save(lectureJpaEntity);
    }

    @Override
    public void createParticipant(Lecture lecture) {

        List<Participant> participants = lecture.getParticipants();
        Participant newParticipant = participants.get(participants.size() - 1);

        lectureToMemberRepository.save(LectureToMemberEntity.builder()
                        .lectureJpaEntity(lectureRepository.findById(lecture.getLectureId().getValue()).get())
                        .memberJpaEntity(memberLoadAdapter.loadMemberJpaEntity(newParticipant.getMemberId().getValue()))
                        .applyDateTime(newParticipant.getApplyDateTime())
                .build());

        LectureJpaEntity lectureJpaEntity = lectureRepository.findById(lecture.getLectureId().getValue()).get();
        lectureJpaEntity.updateEnrollStudent(lecture.getEnrollStudent());
    }
}
