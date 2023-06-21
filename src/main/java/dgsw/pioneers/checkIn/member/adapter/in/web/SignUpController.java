package dgsw.pioneers.checkIn.member.adapter.in.web;

import dgsw.pioneers.checkIn.global.annotation.WebAdapter;
import dgsw.pioneers.checkIn.global.response.Response;
import dgsw.pioneers.checkIn.member.adapter.in.web.request.SignUpRequest;
import dgsw.pioneers.checkIn.member.application.port.in.SignUpCommand;
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
@RequestMapping(value = "/signUp")
@RequiredArgsConstructor
@Tag(name = "signUp", description = "signUp Api")
public class SignUpController {

    private final SignUpUseCase signUpUseCase;

    @PostMapping
    @Operation(summary = "sign up", description = "회원가입")
    public Response sendMoney(@RequestBody SignUpRequest request) {

        SignUpCommand signUpCommand = new SignUpCommand(
                request.getId(),
                request.getName(),
                request.getPw(),
                request.getEmail(),
                request.getMemberRole()
        );

        signUpUseCase.signUpMember(signUpCommand);
        return Response.of(HttpStatus.OK, "회원가입 성공");
    }
}
