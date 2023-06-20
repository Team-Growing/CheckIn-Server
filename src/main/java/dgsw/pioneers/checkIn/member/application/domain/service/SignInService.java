package dgsw.pioneers.checkIn.member.application.domain.service;

import dgsw.pioneers.checkIn.common.annotation.UseCase;
import dgsw.pioneers.checkIn.member.adapter.in.web.request.SignInRequest;
import dgsw.pioneers.checkIn.member.application.port.in.SignInUseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class SignInService implements SignInUseCase {

    @Override
    public void signInMember(SignInRequest request) {

    }
}
