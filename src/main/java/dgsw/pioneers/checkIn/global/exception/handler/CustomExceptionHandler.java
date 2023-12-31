package dgsw.pioneers.checkIn.global.exception.handler;

import dgsw.pioneers.checkIn.global.exception.ErrorResponseEntity;
import dgsw.pioneers.checkIn.global.exception.GlobalExceptionCode;
import dgsw.pioneers.checkIn.global.exception.custom.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    protected ResponseEntity handleCustomException(CustomException e){
        return ErrorResponseEntity.responseEntity(e.getExceptionCode());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity handleException(Exception e){
        log.error(e.toString());
        return ResponseEntity
                .status(500)
                .body(ErrorResponseEntity.builder()
                        .status(GlobalExceptionCode.INTERNAL_SERVER.getStatus().value())
                        .code(GlobalExceptionCode.INTERNAL_SERVER.name())
                        .message(e.getMessage())
                        .build());
    }
}