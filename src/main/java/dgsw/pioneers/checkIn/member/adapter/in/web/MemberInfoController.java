package dgsw.pioneers.checkIn.member.adapter.in.web;

import dgsw.pioneers.checkIn.member.adapter.in.web.dto.res.MemberInfoRes;
import dgsw.pioneers.checkIn.member.application.domain.model.Member;
import dgsw.pioneers.checkIn.global.annotation.AuthCheck;
import dgsw.pioneers.checkIn.global.annotation.WebAdapter;
import dgsw.pioneers.checkIn.global.response.ResponseData;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping(value = "/memberInfo")
@RequiredArgsConstructor
@Tag(name = "memberInfo", description = "memberInfo Api")
public class MemberInfoController {

    @AuthCheck
    @GetMapping("/my")
    public ResponseData<MemberInfoRes> getUser(@RequestAttribute Member member) {
        MemberInfoRes memberInfoRes = MemberInfoRes.convertToDTO(member);
        return ResponseData.of(HttpStatus.OK, "내 정보 가져오기 성공", memberInfoRes);
    }
}
