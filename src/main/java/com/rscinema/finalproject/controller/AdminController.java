package com.rscinema.finalproject.controller;

import com.rscinema.finalproject.domain.dto.UserDTO;
import com.rscinema.finalproject.domain.entity.user.User;
import com.rscinema.finalproject.domain.mapper.UserMapper;
import com.rscinema.finalproject.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserServiceImpl userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<UserDTO>> findAllUsers() {
            return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> findAllAdmins(){
        return ResponseEntity.ok(userService.findAllAdmins());
    }

    @GetMapping("/customers")
    public ResponseEntity<List<UserDTO>> findAllCustomers(){
        return ResponseEntity.ok(userService.findAllCustomers());
    }

    @GetMapping("/deleted")
    public ResponseEntity<List<UserDTO>> findAllDeletedAdmins(){
        return ResponseEntity.ok(userService.findAllDeletedAdmins());
    }

    @GetMapping("/customers/deleted")
    public ResponseEntity<List<UserDTO>> findAllDeletedCustomers(){
        return ResponseEntity.ok(userService.findAllDeletedCustomers());
    }

//    @PostMapping("/update/{id}")
//    public ResponseEntity<UserDTO> update(@PathVariable Integer id,
//            @Valid @RequestBody UserDTO userDTO){
//        User user = UserMapper.toEntity(userService.findUserById(id));
//
//
//    }

}
