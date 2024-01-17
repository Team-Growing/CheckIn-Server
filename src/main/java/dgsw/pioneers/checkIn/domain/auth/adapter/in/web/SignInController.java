package dgsw.pioneers.checkIn.domain.auth.adapter.in.web;

import dgsw.pioneers.checkIn.domain.auth.adapter.in.web.dto.req.SignInReq;
import dgsw.pioneers.checkIn.domain.auth.application.domain.model.Token;
import dgsw.pioneers.checkIn.domain.auth.application.port.in.SignInUseCase;
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

import javax.validation.Valid;

@WebAdapter
@RestController
@RequestMapping(value = "/sign-in")
@RequiredArgsConstructor
@Tag(name = "SignIn", description = "SignIn Api")
public class SignInController {

    private final SignInUseCase signInUseCase;

    @PostMapping
    @Operation(summary = "sign in", description = "로그인")
    public ResponseData<Token> signUpTeacher(
            @RequestBody @Valid SignInReq signInReq
    ) {
        Token token = signInUseCase.signIn(signInReq);
        return ResponseData.of(HttpStatus.OK, "로그인 성공", token);
    }
}
