package dgsw.pioneers.checkIn.domain.attendance.application.domain.exception;

import dgsw.pioneers.checkIn.global.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum AttendanceExceptionCode implements ExceptionCode {

    NOT_MATCH_CODE(HttpStatus.PRECONDITION_FAILED, "강좌 상태와 맞지 않는 요청"),
    ATTENDANCE_DUPLICATED(HttpStatus.GONE, "이미 출석한 강좌");

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
