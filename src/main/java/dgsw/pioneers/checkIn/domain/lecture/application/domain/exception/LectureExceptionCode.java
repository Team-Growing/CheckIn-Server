package dgsw.pioneers.checkIn.domain.lecture.application.domain.exception;

import dgsw.pioneers.checkIn.global.exception.ExceptionCode;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum LectureExceptionCode implements ExceptionCode {

    NOT_MATCH_PERIOD(HttpStatus.PRECONDITION_FAILED, "강좌 상태와 맞지 않는 요청"),
    LECTURE_STUDENT_EXCESS(HttpStatus.BAD_REQUEST, "강좌 인원 초과"),
    LECTURE_DUPLICATED(HttpStatus.GONE, "이미 신청한 강좌"),
    NOT_MATCH_GRADE(HttpStatus.BAD_REQUEST, "강좌 학년과 해당 멤버의 학년이 일치하지 않음");

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
