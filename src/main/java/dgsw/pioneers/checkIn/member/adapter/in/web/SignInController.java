package dgsw.pioneers.checkIn.member.adapter.in.web;

import dgsw.pioneers.checkIn.common.annotation.WebAdapter;
import dgsw.pioneers.checkIn.common.response.Response;
import dgsw.pioneers.checkIn.member.adapter.in.web.request.SignInRequest;
import dgsw.pioneers.checkIn.member.application.port.in.SignInCommand;
import dgsw.pioneers.checkIn.member.application.port.in.SignInUseCase;
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
@RequestMapping(value = "/signIn")
@RequiredArgsConstructor
@Tag(name = "SignIn", description = "SignIn Api")
public class SignInController {

    private final SignInUseCase signInUseCase;

    @PostMapping
    @Operation(summary = "sign in", description = "회원가입")
    public Response sendMoney(@RequestBody SignInRequest request) {

        SignInCommand signInCommand = new SignInCommand(
                request.getId(),
                request.getName(),
                request.getPw(),
                request.getEmail()
        );

        signInUseCase.signInMember(signInCommand);
        return Response.of(HttpStatus.OK, "회원가입 성공");
    }
}
