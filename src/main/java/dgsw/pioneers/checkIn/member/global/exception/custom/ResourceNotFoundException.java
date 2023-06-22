package dgsw.pioneers.checkIn.member.global.exception.custom;

import dgsw.pioneers.checkIn.member.global.exception.GlobalExceptionCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends CustomException{

    public ResourceNotFoundException() {
        super(GlobalExceptionCode.RESOURCE_NOT_FOUND);
    }
}
