package dgsw.pioneers.checkIn.domain.attendance.application.domain.exception;

import dgsw.pioneers.checkIn.global.exception.custom.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class AttendanceCodeNotMatchException extends CustomException {

    public AttendanceCodeNotMatchException() {
        super(AttendanceExceptionCode.CODE_NOT_MATCH);
    }
}
