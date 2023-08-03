package com.rscinema.finalproject.domain.dto.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginRequestDTO {

    @NotEmpty(message = "Email should not be empty!")
    private String email;
    @NotEmpty(message = "Password should not be empty!")
    private String password;
}
