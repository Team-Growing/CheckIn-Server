package dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.res;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TeacherInfoRes {

    private String id;
    private String name;

    public static TeacherInfoRes convertToDTO(Member member) {
        return TeacherInfoRes.builder()
                .id(member.getMemberId().getValue())
                .name(member.getName() + " (" + member.getSubject() + ")")
                .build();
    }
}
