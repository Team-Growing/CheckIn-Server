package dgsw.pioneers.checkIn.global.lib.zonedatetime;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class ZoneDateTimeUtil {

    public LocalDate nowToLocalDate() {
        return now().toLocalDate();
    }

    private ZonedDateTime now() {
        return ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
    }
}
