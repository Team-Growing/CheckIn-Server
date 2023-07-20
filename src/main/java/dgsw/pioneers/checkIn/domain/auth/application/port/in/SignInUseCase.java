package dgsw.pioneers.checkIn.domain.auth.application.port.in;

import dgsw.pioneers.checkIn.domain.auth.adapter.in.web.dto.req.SignInReq;
import dgsw.pioneers.checkIn.domain.auth.application.domain.model.Token;

public interface SignInUseCase {
    Token signIn(SignInReq signInReq);
}
