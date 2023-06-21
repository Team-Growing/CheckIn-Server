package dgsw.pioneers.checkIn.member.application.domain.service;

import dgsw.pioneers.checkIn.common.annotation.UseCase;
import dgsw.pioneers.checkIn.member.application.port.in.SignInCommand;
import dgsw.pioneers.checkIn.member.application.port.in.SignInUseCase;
import dgsw.pioneers.checkIn.member.application.port.out.LoadMemberPort;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class SignInService implements SignInUseCase {

    private final LoadMemberPort loadMemberPort;

    @Override
    public void signInMember(SignInCommand signInCommand) {

        loadMemberPort.loadMember(signInCommand.getMemberId());
    }
}
