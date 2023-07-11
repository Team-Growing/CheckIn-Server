package dgsw.pioneers.checkIn.member.adapter.in.web;

import dgsw.pioneers.checkIn.global.annotation.WebAdapter;
import dgsw.pioneers.checkIn.global.response.Response;
import dgsw.pioneers.checkIn.member.adapter.in.web.dto.req.SignUpStudentReq;
import dgsw.pioneers.checkIn.member.adapter.in.web.dto.req.SignUpTeacherReq;
import dgsw.pioneers.checkIn.member.application.port.in.SignUpStudentCommand;
import dgsw.pioneers.checkIn.member.application.port.in.SignUpTeacherCommand;
import dgsw.pioneers.checkIn.member.application.port.in.SignUpUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping(value = "/sign-up")
@RequiredArgsConstructor
@Tag(name = "signUp", description = "signUp Api")
public class SignUpController {

    private final SignUpUseCase signUpUseCase;

    @PostMapping("/teacher")
    @Operation(summary = "sign up to teacher", description = "선생님 회원가입")
    public Response signUpTeacher(@RequestBody SignUpTeacherReq request) {

        SignUpTeacherCommand signUpCommand = new SignUpTeacherCommand(
                request.getId(),
                request.getName(),
                request.getPw(),
                request.getEmail()
        );

        signUpUseCase.signUpTeacher(signUpCommand);
        return Response.of(HttpStatus.OK, "선생님 회원가입 성공");
    }

    @PostMapping("/student")
    @Operation(summary = "sign up to student", description = "학생 회원가입")
    public Response signUpStudent(@RequestBody SignUpStudentReq request) {

        SignUpStudentCommand signUpCommand = new SignUpStudentCommand(
                request.getId(),
                request.getName(),
                request.getPw(),
                request.getEmail(),
                request.getStudentInfo()
        );

        signUpUseCase.signUpStudent(signUpCommand);
        return Response.of(HttpStatus.OK, "학생 회원가입 성공");
    }
}
