package dgsw.pioneers.checkIn.domain.member.application.domain.exception;

import dgsw.pioneers.checkIn.global.exception.custom.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class MemberDuplicateException extends CustomException {

    public MemberDuplicateException() {
        super(MemberExceptionCode.MEMBER_DUPLICATION);
    }
}
