package dgsw.pioneers.checkIn.domain.lecture.application.domain.exception;

import dgsw.pioneers.checkIn.global.exception.custom.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class LectureGradeNotMatchException extends CustomException {

    public LectureGradeNotMatchException() {
        super(LectureExceptionCode.NOT_MATCH_GRADE);
    }
}
