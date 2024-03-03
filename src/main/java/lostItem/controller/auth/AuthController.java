package lostItem.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lostItem.Exception.collections.InputValid.BindingErrors;
import lostItem.dto.SigninDto;
import lostItem.dto.SignupDto;
import lostItem.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController { // 인증 관련 컨트롤러
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> authSignup(@RequestBody @Valid SignupDto signUpDto, BindingResult bindingResult){
        handleBindingErrors(bindingResult);

        //회원가입 진행
        userService.register(signUpDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/signin")
    public ResponseEntity<?> authSignIn(@RequestBody @Valid SigninDto signinDto, BindingResult bindingResult) {
        // BindingResult 에러 처리
        handleBindingErrors(bindingResult);

        userService.login(signinDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    public void handleBindingErrors(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            throw new BindingErrors(fieldErrors);
        }
    }
}
