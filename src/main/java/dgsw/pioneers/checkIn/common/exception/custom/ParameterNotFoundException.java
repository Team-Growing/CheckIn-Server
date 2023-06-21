package dgsw.pioneers.checkIn.common.exception.custom;

import dgsw.pioneers.checkIn.common.exception.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ParameterNotFoundException extends CustomException {

    public ParameterNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
