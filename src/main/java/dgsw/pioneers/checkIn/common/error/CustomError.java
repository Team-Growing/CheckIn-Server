package dgsw.pioneers.checkIn.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomError extends RuntimeException {
    private final ErrorCode errorCode;

    public static CustomError of(ErrorCode errorCode){
        return new CustomError(errorCode);
    }
}