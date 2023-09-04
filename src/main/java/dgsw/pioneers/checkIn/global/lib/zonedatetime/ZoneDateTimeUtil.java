package dgsw.pioneers.checkIn.global.lib.zonedatetime;

import java.time.*;

public class ZoneDateTimeUtil {

    public static LocalDateTime nowToLocalDateTime() {
        return ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDateTime();
    }

    public static LocalDate nowToLocalDate() {
        return ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalDate();
    }

    public static LocalTime nowToLocalTime() {
        return ZonedDateTime.now(ZoneId.of("Asia/Seoul")).toLocalTime();
    }
}
