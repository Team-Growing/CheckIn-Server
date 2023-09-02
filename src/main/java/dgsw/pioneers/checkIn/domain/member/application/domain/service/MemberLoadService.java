package dgsw.pioneers.checkIn.domain.member.application.domain.service;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.port.in.MemberLoadUseCase;
import dgsw.pioneers.checkIn.domain.member.application.port.out.LoadMemberPort;
import dgsw.pioneers.checkIn.global.annotation.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberLoadService implements MemberLoadUseCase {

    private final LoadMemberPort loadMemberPort;

    @Override
    public Member loadMember(Member.MemberId memberId) {
        return loadMemberPort.loadMember(memberId);
    }
}
