package com.rscinema.finalproject.controller;

import com.rscinema.finalproject.configuration.AuthenticationService;
import com.rscinema.finalproject.domain.dto.auth.LoginRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {


    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public String loginWithToken(@RequestBody LoginRequestDTO loginReq){
        return "Bearer ".concat(authenticationService.generateToken(loginReq));
    }
}
