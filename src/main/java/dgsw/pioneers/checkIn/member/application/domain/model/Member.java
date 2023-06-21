package dgsw.pioneers.checkIn.member.application.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

    private final MemberId memberId;

    private final String name;

    private String pw;

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
