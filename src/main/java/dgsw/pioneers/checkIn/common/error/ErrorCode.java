package dgsw.pioneers.checkIn.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 유저"),
    INVALID_PERMISSION(HttpStatus.BAD_REQUEST, "유효하지 않은 권한"),
    INVALID_TOKEN(HttpStatus.FORBIDDEN, "유효하지 않은 토큰"),
    TOKEN_NOT_PROVIDED(HttpStatus.BAD_REQUEST, "토큰이 입력되지 않았습니다"),
    TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "만료된 토큰"),
    WEB_CLIENT_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류"),
    INTERNAL_SERVER(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류");

    private final HttpStatus status;
    private final String message;
}
