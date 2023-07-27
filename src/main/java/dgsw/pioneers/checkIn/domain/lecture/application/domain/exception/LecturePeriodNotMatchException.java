package dgsw.pioneers.checkIn.domain.lecture.application.domain.exception;

import dgsw.pioneers.checkIn.global.exception.custom.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class LecturePeriodNotMatchException extends CustomException {

    public LecturePeriodNotMatchException() {
        super(LectureExceptionCode.NOT_MATCH_PERIOD);
    }
}
