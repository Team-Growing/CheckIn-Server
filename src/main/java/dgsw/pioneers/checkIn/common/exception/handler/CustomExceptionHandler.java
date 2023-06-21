package dgsw.pioneers.checkIn.common.exception.handler;

import dgsw.pioneers.checkIn.common.exception.ExceptionCode;
import dgsw.pioneers.checkIn.common.exception.ErrorResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomError.class)
    protected ResponseEntity handleCustomException(CustomError e){
        return ErrorResponseEntity.responseEntity(e.getExceptionCode());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity handleException(Exception e){
        log.error(e.toString());
        return ResponseEntity
                .status(500)
                .body(ErrorResponseEntity.builder()
                        .status(ExceptionCode.INTERNAL_SERVER.getStatus().value())
                        .code(ExceptionCode.INTERNAL_SERVER.name())
                        .message(e.getMessage())
                        .build());
    }
}