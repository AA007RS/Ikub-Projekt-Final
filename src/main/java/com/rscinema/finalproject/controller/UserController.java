package com.rscinema.finalproject.controller;

import com.rscinema.finalproject.domain.dto.UserDTO;
import com.rscinema.finalproject.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> findAllUsers() {
            return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/admins")
    public ResponseEntity<List<UserDTO>> findAllAdmins(){
        return ResponseEntity.ok(userService.findAllAdmins());
    }

    @GetMapping("/customers")
    public ResponseEntity<List<UserDTO>> findAllCustomers(){
        return ResponseEntity.ok(userService.findAllCustomers());
    }

    @GetMapping("/admins/deleted")
    public ResponseEntity<List<UserDTO>> findAllDeletedAdmins(){
        return ResponseEntity.ok(userService.findAllDeletedAdmins());
    }

    @GetMapping("/customers/deleted")
    public ResponseEntity<List<UserDTO>> findAllDeletedCustomers(){
        return ResponseEntity.ok(userService.findAllDeletedCustomers());
    }
}
