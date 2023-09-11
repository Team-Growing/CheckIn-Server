package dgsw.pioneers.checkIn.domain.attendance.application.domain.exception;

import dgsw.pioneers.checkIn.global.exception.custom.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AttendantNotFoundException extends CustomException {

    public AttendantNotFoundException() {
        super(AttendanceExceptionCode.ATTENDANT_NOT_FOUND);
    }
}
