package com.rscinema.finalproject.controller;

import com.rscinema.finalproject.domain.dto.user.UserDTO;
import com.rscinema.finalproject.domain.dto.user.RegistrationDetailsDTO;
import com.rscinema.finalproject.domain.dto.auth.RegistrationFormDTO;
import com.rscinema.finalproject.domain.dto.password.ChangePasswordFormDTO;
import com.rscinema.finalproject.domain.mapper.UserMapper;
import com.rscinema.finalproject.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody RegistrationFormDTO registrationFormDTO){
        UserDTO toSave = userService.registerUser(registrationFormDTO);
        return new ResponseEntity<>(toSave, HttpStatus.CREATED);
    }
    @PutMapping("/update-details/{id}")
    public ResponseEntity<UserDTO> updateUserDetails(
            @PathVariable("id") Integer id,
            @Valid @RequestBody RegistrationDetailsDTO dto
    ) {
        dto.setId(id);
        return ResponseEntity.ok(userService.updateDetails(dto));
    }

    @PutMapping("/change-password/{id}")
    public ResponseEntity<String> changePassword(
            @PathVariable("id") Integer id,
            @Valid @RequestBody ChangePasswordFormDTO dto
    ) {
        dto.setId(id);
        return ResponseEntity.ok(userService.updatePassword(dto));
    }

    //endpoint per userin normal qe sheh te dhenat e veta
    @GetMapping("/details/{id}")
    public ResponseEntity<UserDTO> seeUserDetails(@PathVariable("id")Integer id){
        return ResponseEntity.ok(userService.seeUserDetails(id));
    }

    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    @PutMapping("/deleteAccount/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable("id")Integer id){
        return ResponseEntity.ok(userService.deleteAccount(id));
    }

}
