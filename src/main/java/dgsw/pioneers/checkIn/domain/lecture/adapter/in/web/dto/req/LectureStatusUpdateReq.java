package dgsw.pioneers.checkIn.domain.lecture.adapter.in.web.dto.req;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.enums.LectureStatus;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class LectureStatusUpdateReq {

    @NotNull
    private LectureStatus lectureStatus;
}
