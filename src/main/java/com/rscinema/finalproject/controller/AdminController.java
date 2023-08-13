package com.rscinema.finalproject.controller;

import com.rscinema.finalproject.domain.dto.user.UserDTO;
import com.rscinema.finalproject.domain.dto.user.UserSearchDTO;
import com.rscinema.finalproject.domain.entity.user.User;
import com.rscinema.finalproject.service.impl.UserServiceImpl;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/customers")
    public ResponseEntity<List<UserDTO>> findAllCustomers(){
        return ResponseEntity.ok(userService.findAllCustomers());
    }

    @GetMapping("/customers/deleted")
    public ResponseEntity<List<UserDTO>> findAllDeletedCustomers(){
        return ResponseEntity.ok(userService.findAllDeletedCustomers());
    }

    @GetMapping("/users/search")
    public ResponseEntity<List<UserDTO>> search( @RequestBody UserSearchDTO dto){
        return ResponseEntity.ok(userService.search(dto));
    }

}
