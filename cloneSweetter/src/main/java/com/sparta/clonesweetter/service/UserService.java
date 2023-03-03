package com.sparta.clonesweetter.service;

import com.sparta.clonesweetter.dto.LoginRequestDto;
import com.sparta.clonesweetter.dto.SignupRequestDto;
import com.sparta.clonesweetter.dto.UserResponseDto;
import com.sparta.clonesweetter.entity.User;
import com.sparta.clonesweetter.entity.UserRoleEnum;
import com.sparta.clonesweetter.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;
@Service
@Validated
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    @Transactional
    public void signup(@Valid SignupRequestDto signupRequestDto){
        String username = signupRequestDto.getUsername();
        String password = "null"; // = passwordEncoder.encode(signupRequestDto.getPassword());
        String nickname = signupRequestDto.getNickname();
        String email = signupRequestDto.getEmail();

        Optional<User> foundUsername = userRepository.findByUsername(username);
        if (foundUsername.isPresent()){
            throw new IllegalArgumentException("중복된 사용자 존재");
        }
        Optional<User> foundEmail = userRepository.findByEmail(email);
        if (foundEmail.isPresent()){
            throw new IllegalArgumentException("중복된 이메일 존재");
        }
        Optional<User> foundNickname = userRepository.findByNickname(nickname);
        if (foundNickname.isPresent()){
            throw new IllegalArgumentException("중복된 닉네임 존재");
        }
        UserRoleEnum role = UserRoleEnum.USER;

        User user = new User(username, password, nickname, email, role);
        userRepository.save(user);
    }

/*    @Transactional(readOnly = true)
    public UserResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse response){
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new IllegalArgumentException("등록된 사용자가 없습니다")
        );

        if (!passwordEncoder.matches(password, user.getPassword())){
            throw new IllegalArgumentException("비밀 번호가 틀렸습니다.");
        }

        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));
        return new UserResponseDto(user);
    }*/
}
