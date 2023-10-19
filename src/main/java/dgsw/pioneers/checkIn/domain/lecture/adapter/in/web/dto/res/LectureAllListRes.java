package dgsw.pioneers.checkIn.domain.lecture.adapter.in.web.dto.res;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class LectureAllListRes {

    private List<Lecture> waitingPeriod;
    private List<Lecture> enrolment;
    private List<Lecture> coursePeriod;
    private List<Lecture> termination;
}
