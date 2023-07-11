package dgsw.pioneers.checkIn.lecture.application.port.in;

import dgsw.pioneers.checkIn.global.lib.valid.SelfValidating;
import dgsw.pioneers.checkIn.lecture.application.domain.model.AcceptableStudent;
import dgsw.pioneers.checkIn.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.lecture.application.domain.model.LectureSchedule;
import dgsw.pioneers.checkIn.lecture.application.domain.model.LectureTeacher;
import dgsw.pioneers.checkIn.lecture.application.domain.model.enums.PlaceType;
import dgsw.pioneers.checkIn.member.application.domain.model.Member;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@EqualsAndHashCode(callSuper = false)
public class LectureGenerateCommand extends SelfValidating<LectureGenerateCommand> {

    @NotNull String explanation;
    @NotNull PlaceType placeType;
    @NotNull AcceptableStudent acceptableStudent;
    @NotNull LectureSchedule lectureSchedule;

    public LectureGenerateCommand(
            String explanation,
            PlaceType placeType,
            AcceptableStudent acceptableStudent,
            LectureSchedule lectureSchedule
            ) {
        this.explanation = explanation;
        this.placeType = placeType;
        this.acceptableStudent = acceptableStudent;
        this.lectureSchedule = lectureSchedule;
        this.validateSelf();
    }

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
