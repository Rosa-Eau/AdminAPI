package com.sparta.classapi.domain.user.controller;

import com.sparta.classapi.domain.user.dto.LoginRequestDto;
import com.sparta.classapi.domain.user.dto.SignupRequestDto;
import com.sparta.classapi.domain.user.service.UserService;
import com.sparta.classapi.global.handler.ValidHelper;
import com.sparta.classapi.global.security.SecurityUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/api/user")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto requestDto, BindingResult bindingResult) {
        // Validation 예외처리
        ValidHelper.validation(bindingResult);
        // validation 통과 시 로직 수행
        // JwtAuthenticationFilter를 사용하여 로그인 시도
        // 해당 필터에서 처리 후, JWT 토큰이 생성되고 응답에 추가됨
        // 성공하면 200 OK, 실패하면 401 Unauthorized 반환
        return ResponseEntity.ok("로그인에 성공하셨습니다.");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequestDto requestDto, BindingResult bindingResult) {
        SecurityUtil.getCurrentUserId();
        // Validation 예외처리
        ValidHelper.validation(bindingResult);

        try {
            userService.signup(requestDto);
            return ResponseEntity.ok("회원가입에 성공하였습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

//    private ResponseEntity<?> validation(BindingResult bindingResult) {
//        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//        if (!fieldErrors.isEmpty()) {
//            for (FieldError fieldError : bindingResult.getFieldErrors()) {
//                log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
//            }
//        }
//        return null;
//    }

}
