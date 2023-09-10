package dgsw.pioneers.checkIn.domain.attendance.application.domain.model;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.global.lib.zonedatetime.ZoneDateTimeUtil;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Attendant {

    Member.MemberId attendantId;
    LocalDateTime applyDateTime;

    public static Attendant generate(Member.MemberId memberId) {
        return new Attendant(memberId, ZoneDateTimeUtil.nowToLocalDateTime());
    }
}
