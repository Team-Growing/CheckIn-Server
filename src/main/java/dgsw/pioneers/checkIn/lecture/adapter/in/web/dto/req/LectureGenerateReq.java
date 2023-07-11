package dgsw.pioneers.checkIn.lecture.adapter.in.web.dto.req;

import dgsw.pioneers.checkIn.lecture.application.domain.model.AcceptableStudent;
import dgsw.pioneers.checkIn.lecture.application.domain.model.LectureSchedule;
import dgsw.pioneers.checkIn.lecture.application.domain.model.enums.PlaceType;
import lombok.Getter;

@Getter
public class LectureGenerateReq {

    private String explanation;
    private PlaceType placeType;
    private AcceptableStudent acceptableStudent;
    private LectureSchedule lectureSchedule;
}
