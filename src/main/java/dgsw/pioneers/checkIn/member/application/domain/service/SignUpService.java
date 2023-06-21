package dgsw.pioneers.checkIn.member.application.domain.service;

import dgsw.pioneers.checkIn.global.annotation.UseCase;
import dgsw.pioneers.checkIn.member.application.domain.exception.DuplicateMemberException;
import dgsw.pioneers.checkIn.member.application.port.in.SignUpCommand;
import dgsw.pioneers.checkIn.member.application.port.in.SignUpUseCase;
import dgsw.pioneers.checkIn.member.application.port.out.CreateMemberPort;
import dgsw.pioneers.checkIn.member.application.port.out.ExistMemberPort;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@Transactional
@RequiredArgsConstructor
public class SignUpService implements SignUpUseCase {

    private final ExistMemberPort existMemberPort;
    private final CreateMemberPort createMemberPort;

    @Override
    public void signUpMember(SignUpCommand signUpCommand) {

        if (existMemberPort.existByMemberId(signUpCommand.getMemberId())) {
            throw new DuplicateMemberException();
        }

        createMemberPort.createMember(signUpCommand.mapToDomainEntity());
    }
}
