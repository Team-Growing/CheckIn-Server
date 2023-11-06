package dgsw.pioneers.checkIn.domain.attendance.application.domain.exception;

import dgsw.pioneers.checkIn.global.exception.custom.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AttendanceCodeForbiddenException extends CustomException {

    public AttendanceCodeForbiddenException() {
        super(AttendanceExceptionCode.CODE_FORBIDDEN);
    }
}
