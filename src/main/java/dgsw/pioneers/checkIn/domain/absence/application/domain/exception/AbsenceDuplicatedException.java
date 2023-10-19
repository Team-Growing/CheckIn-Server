package dgsw.pioneers.checkIn.domain.absence.application.domain.exception;

import dgsw.pioneers.checkIn.global.exception.custom.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.GONE)
public class AbsenceDuplicatedException extends CustomException {

    public AbsenceDuplicatedException() {
        super(AbsenceExceptionCode.ABSENCE_DUPLICATED);
    }
}
