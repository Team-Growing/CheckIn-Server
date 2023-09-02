package dgsw.pioneers.checkIn.domain.lecture.application.domain.exception;

import dgsw.pioneers.checkIn.global.exception.custom.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.GONE)
public class LectureDuplicatedException extends CustomException {

    public LectureDuplicatedException() {
        super(LectureExceptionCode.LECTURE_DUPLICATED);
    }
}
