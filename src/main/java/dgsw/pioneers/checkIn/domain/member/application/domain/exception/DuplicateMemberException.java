package dgsw.pioneers.checkIn.domain.member.application.domain.exception;

import dgsw.pioneers.checkIn.global.exception.custom.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class DuplicateMemberException extends CustomException {

    public DuplicateMemberException() {
        super(MemberExceptionCode.MEMBER_DUPLICATION);
    }
}
