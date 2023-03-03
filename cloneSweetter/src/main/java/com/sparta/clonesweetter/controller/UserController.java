package com.sparta.clonesweetter.controller;

import com.sparta.clonesweetter.dto.*;
import com.sparta.clonesweetter.security.UserDetailsImpl;
import com.sparta.clonesweetter.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    //회원가입 완료 후 반환값 > FE와 협의 필요 - 정환
    @PostMapping("/user/signup")
    public ModelAndView signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
    //로그인 후 페이지 구성에 사용할 유저정보 리턴 - 정환
    @PostMapping("/user/login")
    public UserResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response){
        return userService.login(loginRequestDto, response);
    }

    @PostMapping("/user/checkpw")
    public boolean checkPassword(@RequestBody PasswordRequestDto passwordRequestDto,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.checkPassword(passwordRequestDto, userDetails.getUser());
    }
    //내 정보 변경
    @PutMapping("/mypage")
    public UserResponseDto updateProfile(@RequestBody UserRequestDto userRequestDto,
                                         @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.updateProfile(userRequestDto, userDetails.getUser());
    }
}
