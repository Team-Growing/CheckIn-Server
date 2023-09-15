package dgsw.pioneers.checkIn.domain.lecture.adapter.in.web.dto.req;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.LectureSchedule;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.AcceptableStudent;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.LectureTeacher;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureTag;
import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.PlaceType;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class LectureGenerateReq {

    @NotBlank
    private String lectureName;
    @NotNull
    private String explanation;
    @NotNull
    private PlaceType placeType;
    @NotNull
    private LectureTag lectureTag;
    @NotBlank
    private String teacherId;
    @NotNull
    private AcceptableStudent acceptableStudent;
    @NotNull
    private LectureSchedule lectureSchedule;

    public Lecture mapToDomainEntity() {
        return Lecture.teacherWithId(
                this.lectureName,
                this.explanation,
                this.placeType,
                this.lectureTag,
                this.acceptableStudent,
                LectureTeacher.builder()
                        .teacherId(new Member.MemberId(teacherId)).build(),
                this.lectureSchedule
        );
    }
}
