package dgsw.pioneers.checkIn.domain.absence.application.domain.exception;

import dgsw.pioneers.checkIn.global.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum AbsenceExceptionCode implements ExceptionCode {

    ABSENCE_NOT_MATCH(HttpStatus.PRECONDITION_FAILED, "해당 강의를 수강하지 않음"),
    ABSENCE_DUPLICATED(HttpStatus.GONE, "이미 신청한 결강");

    private final HttpStatus status;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return this.status;
    }

    @Override
    public String getExceptionName() {
        return this.name();
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
