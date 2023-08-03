package com.rscinema.finalproject.controller;

import com.rscinema.finalproject.configuration.AuthenticationService;
import com.rscinema.finalproject.domain.dto.auth.LoginRequestDTO;
import com.rscinema.finalproject.domain.dto.auth.RegistrationFormDTO;
import com.rscinema.finalproject.domain.dto.UserDTO;
import com.rscinema.finalproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody RegistrationFormDTO registrationFormDTO){
        UserDTO toSave = userService.registerUser(registrationFormDTO);
        return new ResponseEntity<>(toSave, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public String loginWithToken(@RequestBody LoginRequestDTO loginReq){
        return "Bearer ".concat(authenticationService.generateToken(loginReq));
    }
}
