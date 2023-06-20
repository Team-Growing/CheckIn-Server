package dgsw.pioneers.checkIn.common.error.handler;

import dgsw.pioneers.checkIn.common.error.CustomError;
import dgsw.pioneers.checkIn.common.error.ErrorCode;
import dgsw.pioneers.checkIn.common.error.ErrorResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomError.class)
    protected ResponseEntity handleCustomException(CustomError e){
        return ErrorResponseEntity.responseEntity(e.getErrorCode());
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity handleException(Exception e){
        log.error(e.toString());
        return ResponseEntity
                .status(500)
                .body(ErrorResponseEntity.builder()
                        .status(ErrorCode.INTERNAL_SERVER.getStatus().value())
                        .code(ErrorCode.INTERNAL_SERVER.name())
                        .message(e.getMessage())
                        .build());
    }
}