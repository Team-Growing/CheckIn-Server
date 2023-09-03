package dgsw.pioneers.checkIn.domain.member.adapter.in.web;

import dgsw.pioneers.checkIn.domain.lecture.application.domain.model.Lecture;
import dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.res.MemberInfoRes;
import dgsw.pioneers.checkIn.domain.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.domain.member.application.port.in.MemberLectureLoadUseCase;
import dgsw.pioneers.checkIn.global.annotation.AuthCheck;
import dgsw.pioneers.checkIn.global.annotation.WebAdapter;
import dgsw.pioneers.checkIn.global.response.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
@RequestMapping(value = "/memberInfo")
@RequiredArgsConstructor
@Tag(name = "MemberInfo", description = "MemberInfo Api")
public class MemberInfoController {

    private final MemberLectureLoadUseCase memberLectureLoadUseCase;

    @AuthCheck
    @Operation(summary = "load memberInfo", description = "내 정보 불러오기", security = @SecurityRequirement(name = "Authorization"))
    @GetMapping("/my")
    public ResponseData<MemberInfoRes> getMemberInfo(@RequestAttribute Member member) {
        MemberInfoRes memberInfoRes = MemberInfoRes.convertToDTO(member);
        return ResponseData.of(HttpStatus.OK, "내 정보 불러오기 성공", memberInfoRes);
    }

    @AuthCheck
    @Operation(summary = "load member lectures", description = "내 강좌 정보 불러오기", security = @SecurityRequirement(name = "Authorization"))
    @GetMapping("/lectures/my")
    public ResponseData<List<Lecture>> getMemberLecture(@RequestAttribute Member member) {
        List<Lecture> lectures = memberLectureLoadUseCase.loadLecture(member.getMemberId());
        return ResponseData.of(HttpStatus.OK, "내 강좌 정보 불러오기 성공", lectures);
    }
}
