package dgsw.pioneers.checkIn.auth.adapter.in.web;

import dgsw.pioneers.checkIn.global.annotation.WebAdapter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequestMapping(value = "/SignIn")
@RequiredArgsConstructor
@Tag(name = "SignIn", description = "SignIn Api")
public class SignInController {
}
