package dgsw.pioneers.checkIn.auth.adapter.in.web;

import dgsw.pioneers.checkIn.auth.adapter.in.web.dto.req.SignInReq;
import dgsw.pioneers.checkIn.auth.application.domain.model.Token;
import dgsw.pioneers.checkIn.auth.application.port.in.SignInCommand;
import dgsw.pioneers.checkIn.auth.application.port.in.SignInUseCase;
import dgsw.pioneers.checkIn.global.annotation.WebAdapter;
import dgsw.pioneers.checkIn.global.response.ResponseData;
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
@RequestMapping(value = "/sign-in")
@RequiredArgsConstructor
@Tag(name = "SignIn", description = "SignIn Api")
public class SignInController {

    private final SignInUseCase signInUseCase;

    @PostMapping
    @Operation(summary = "sign in", description = "로그인")
    public ResponseData<Token> signUpTeacher(@RequestBody SignInReq request) {

        SignInCommand signInCommand = new SignInCommand(
                request.getId(),
                request.getPw()
        );

        Token token = signInUseCase.signIn(signInCommand);
        return ResponseData.of(HttpStatus.OK, "로그인 성공", token);
    }
}
