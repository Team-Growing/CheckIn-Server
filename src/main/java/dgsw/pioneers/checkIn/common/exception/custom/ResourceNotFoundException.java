package dgsw.pioneers.checkIn.common.exception.custom;

import dgsw.pioneers.checkIn.common.exception.ExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends CustomException{

    public ResourceNotFoundException(ExceptionCode exceptionCode) {
        super(exceptionCode);
    }
}
