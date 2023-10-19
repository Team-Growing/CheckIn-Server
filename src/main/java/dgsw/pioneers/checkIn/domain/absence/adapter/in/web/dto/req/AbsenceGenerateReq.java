package dgsw.pioneers.checkIn.domain.absence.adapter.in.web.dto.req;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
public class AbsenceGenerateReq {

    @NotNull
    @Positive
    private long lectureId;
    @NotBlank
    private String reason;
}
