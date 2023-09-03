package dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.adapter;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.global.annotation.PersistenceAdapter;
import dgsw.pioneers.checkIn.global.exception.custom.ResourceNotFoundException;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.LectureMapper;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.LectureRepository;
import dgsw.pioneers.checkIn.domain.lecture.adapter.out.persistence.aggregate.LectureJpaEntity;
import dgsw.pioneers.checkIn.domain.lecture.application.port.out.LoadLecturePort;
import lombok.RequiredArgsConstructor;

import java.time.DayOfWeek;
import java.util.List;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class LectureLoadAdapter implements LoadLecturePort {

    private final LectureRepository lectureRepository;
    private final ParticipantLoadAdapter participantLoadAdapter;
    private final LectureMapper lectureMapper;

    @Override
    public Lecture loadLecture(Long lectureId) {

        LectureJpaEntity lectureJpaEntity = lectureRepository.findById(lectureId)
                .orElseThrow(ResourceNotFoundException::new);
        return lectureMapper.mapToDomainEntity(lectureJpaEntity);
    }

    @Override
    public List<Lecture> loadAllLectureByStatus(LectureStatus lectureStatus) {

        return lectureRepository.findAllByLectureStatus(lectureStatus).stream()
                .map(lectureMapper::mapToDomainEntity).collect(Collectors.toList());
    }

    @Override
    public List<Lecture> loadAllLectureByMemberAndStatus(Member.MemberId memberId, LectureStatus lectureStatus) {

        return participantLoadAdapter.loadAllLectureByMemberAndLectureStatus(memberId, lectureStatus)
                .stream().map(lectureToMember -> lectureMapper.mapToDomainEntity(lectureToMember.getLectureJpaEntity()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Lecture> loadAllLectureByDayOfWeek(LectureStatus lectureStatus, DayOfWeek dayOfWeek) {

        return lectureRepository.findAllByLectureStatusAndLectureScheduleDayOfWeek(lectureStatus, dayOfWeek).stream()
                .map(lectureMapper::mapToDomainEntity).collect(Collectors.toList());
    }

    public LectureJpaEntity loadLectureJpaEntity(Long lectureId) {

        return lectureRepository.findById(lectureId)
                .orElseThrow(ResourceNotFoundException::new);
    }
}
