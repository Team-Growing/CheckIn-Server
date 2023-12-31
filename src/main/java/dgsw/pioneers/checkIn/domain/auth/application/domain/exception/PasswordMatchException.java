package dgsw.pioneers.checkIn.domain.auth.application.domain.exception;

import dgsw.pioneers.checkIn.global.exception.custom.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class PasswordMatchException extends CustomException {

    public PasswordMatchException() {
        super(AuthExceptionCode.PASSWORD_NOT_MATCH);
    }
}
