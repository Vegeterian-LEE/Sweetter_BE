package com.sparta.clonesweetter.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class PasswordRequestDto {
    @NotNull
    private String password;
}

