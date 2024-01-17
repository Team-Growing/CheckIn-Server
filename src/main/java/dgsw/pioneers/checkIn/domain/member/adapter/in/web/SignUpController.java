package dgsw.pioneers.checkIn.domain.member.adapter.in.web;

import dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.req.SignUpStudentReq;
import dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto.req.SignUpTeacherReq;
import dgsw.pioneers.checkIn.domain.member.application.port.in.SignUpUseCase;
import dgsw.pioneers.checkIn.global.annotation.WebAdapter;
import dgsw.pioneers.checkIn.global.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@WebAdapter
@RestController
@RequestMapping(value = "/sign-up")
@RequiredArgsConstructor
@Tag(name = "SignUp", description = "SignUp Api")
public class SignUpController {

    private final SignUpUseCase signUpUseCase;

    @PostMapping("/teacher")
    @Operation(summary = "sign up to teacher", description = "선생님 회원가입")
    public Response signUpTeacher(
            @RequestBody @Valid SignUpTeacherReq signUpTeacherReq
    ) {
        signUpUseCase.signUpTeacher(signUpTeacherReq.mapToDomainEntity());
        return Response.of(HttpStatus.OK, "선생님 회원가입 성공");
    }

    @PostMapping("/student")
    @Operation(summary = "sign up to student", description = "학생 회원가입")
    public Response signUpStudent(
            @RequestBody @Valid SignUpStudentReq signUpStudentReq
    ) {
        signUpUseCase.signUpStudent(signUpStudentReq.mapToDomainEntity());
        return Response.of(HttpStatus.OK, "학생 회원가입 성공");
    }
}
