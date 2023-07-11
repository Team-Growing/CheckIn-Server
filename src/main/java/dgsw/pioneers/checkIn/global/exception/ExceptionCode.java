package dgsw.pioneers.checkIn.global.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionCode {

    HttpStatus getHttpStatus();
    String getExceptionName();
    String getMessage();
}
