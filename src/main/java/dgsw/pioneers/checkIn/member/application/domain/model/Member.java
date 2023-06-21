package dgsw.pioneers.checkIn.member.application.domain.model;

import dgsw.pioneers.checkIn.member.application.domain.model.enums.MemberRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

    private final MemberId memberId;

    @Getter
    private final String name;

    @Getter
    private String pw;

    @Getter
    private final String email;

    private final MemberRole memberRole;

    @Value
    public static class MemberId {
        String value;
    }

    public static Member withId(
            MemberId memberId,
            String name,
            String pw,
            String email,
            MemberRole memberRole) {
        return new Member(memberId, name, pw, email, memberRole);
    }
}
