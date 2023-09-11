package dgsw.pioneers.checkIn.domain.attendance.application.domain.exception;

import dgsw.pioneers.checkIn.global.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum AttendanceExceptionCode implements ExceptionCode {

    CODE_NOT_MATCH(HttpStatus.PRECONDITION_FAILED, "강좌 상태와 맞지 않는 요청"),
    ATTENDANCE_DUPLICATED(HttpStatus.GONE, "이미 출석한 강좌"),
    ATTENDANT_NOT_MATCH(HttpStatus.PRECONDITION_FAILED, "출석 명단에 존재하지 않음"),
    ATTENDANT_NOT_FOUND(HttpStatus.NOT_FOUND, "찾을 수 없는 출석자");

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
