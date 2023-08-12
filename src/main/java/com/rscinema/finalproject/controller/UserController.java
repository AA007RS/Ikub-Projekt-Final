package com.rscinema.finalproject.controller;

import com.rscinema.finalproject.configuration.SecurityUtils;
import com.rscinema.finalproject.domain.dto.UserDTO;
import com.rscinema.finalproject.domain.dto.RegistrationDetailsDTO;
import com.rscinema.finalproject.domain.dto.auth.RegistrationFormDTO;
import com.rscinema.finalproject.domain.dto.password.ChangePasswordFormDTO;
import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import com.rscinema.finalproject.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody RegistrationFormDTO registrationFormDTO){
        UserDTO toSave = userService.registerUser(registrationFormDTO);
        return new ResponseEntity<>(toSave, HttpStatus.CREATED);
    }
    @PutMapping("/update-details/{id}")
    public ResponseEntity<UserDTO> registerUserDetails(

            @PathVariable("id") Integer id,
            @Valid @RequestBody RegistrationDetailsDTO dto
    ) {
        dto.setId(id);
        return ResponseEntity.ok(userService.updateDetails(dto));
    }

    @PutMapping("/change-password/{id}")
    public ResponseEntity<UserDTO> changePassword(
            @PathVariable("id") Integer id,
            @Valid @RequestBody ChangePasswordFormDTO dto
    ) {
        dto.setId(id);
        return ResponseEntity.ok(userService.updatePassword(dto));
    }

}
