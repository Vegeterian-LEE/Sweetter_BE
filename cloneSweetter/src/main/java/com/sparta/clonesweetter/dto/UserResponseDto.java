package com.sparta.clonesweetter.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponseDto {
    private String username;
    private String nickname;
    private String image;
    private String introduction;
    private String email;
}