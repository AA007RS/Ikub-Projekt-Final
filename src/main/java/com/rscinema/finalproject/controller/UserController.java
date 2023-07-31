package com.rscinema.finalproject.controller;

import com.rscinema.finalproject.domain.dto.UserDTO;
import com.rscinema.finalproject.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;

        @GetMapping("/{id}")
        public ResponseEntity<UserDTO> findUserById(@PathVariable("id")Integer id){
            return  ResponseEntity.ok(userService.findUserById(id));
        }

}
