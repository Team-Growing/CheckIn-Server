package dgsw.pioneers.checkIn.domain.lecture.adapter.in.web.dto.req;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.LectureSchedule;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.AcceptableStudent;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.LectureTeacher;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.PlaceType;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class LectureGenerateReq {

    @NotNull
    private String explanation;
    @NotNull
    private PlaceType placeType;
    @NotNull
    private AcceptableStudent acceptableStudent;
    @NotNull
    private LectureSchedule lectureSchedule;

    public Lecture mapToDomainEntity(Member.MemberId teacherId) {
        return Lecture.teacherWithId(
                this.explanation,
                this.placeType,
                this.acceptableStudent,
                LectureTeacher.builder()
                        .memberId(teacherId).build(),
                this.lectureSchedule
        );
    }
}