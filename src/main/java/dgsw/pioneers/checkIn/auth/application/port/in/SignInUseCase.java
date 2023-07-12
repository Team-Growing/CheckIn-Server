package dgsw.pioneers.checkIn.auth.application.port.in;

import dgsw.pioneers.checkIn.auth.adapter.in.web.dto.req.SignInReq;
import dgsw.pioneers.checkIn.auth.application.domain.model.Token;

public interface SignInUseCase {
    Token signIn(SignInReq signInReq);
}
