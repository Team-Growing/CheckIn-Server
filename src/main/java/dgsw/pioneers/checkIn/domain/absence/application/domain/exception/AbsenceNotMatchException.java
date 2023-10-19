package dgsw.pioneers.checkIn.domain.absence.application.domain.exception;

import dgsw.pioneers.checkIn.global.exception.custom.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PRECONDITION_FAILED)
public class AbsenceNotMatchException extends CustomException {

    public AbsenceNotMatchException() {
        super(AbsenceExceptionCode.ABSENCE_NOT_MATCH);
    }
}
