package dgsw.pioneers.checkIn.auth.adapter.in.web;

import dgsw.pioneers.checkIn.common.annotation.WebAdapter;
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
@RequestMapping(value = "/signUp")
@RequiredArgsConstructor
@Tag(name = "SignUp", description = "SignUp Api")
public class SignUpController {
}
