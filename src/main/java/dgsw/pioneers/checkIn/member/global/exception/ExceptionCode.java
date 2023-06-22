package dgsw.pioneers.checkIn.member.global.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionCode {

    HttpStatus getHttpStatus();
    String getExceptionName();

    String getMessage();
}
