package dgsw.pioneers.checkIn.global.exception.custom;

import dgsw.pioneers.checkIn.global.exception.GlobalExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class PermissionInvalidException extends CustomException {

    public PermissionInvalidException() {
        super(GlobalExceptionCode.INVALID_PERMISSION);
    }
}
