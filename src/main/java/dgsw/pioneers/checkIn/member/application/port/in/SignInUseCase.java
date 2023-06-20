package dgsw.pioneers.checkIn.member.application.port.in;

import dgsw.pioneers.checkIn.member.adapter.in.web.request.SignInRequest;

import javax.validation.Valid;

public interface SignInUseCase {
    void signInMember(@Valid SignInRequest request);
}
