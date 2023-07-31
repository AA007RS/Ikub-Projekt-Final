package com.rscinema.finalproject.service.impl;

import com.rscinema.finalproject.domain.dto.RegistrationFormDTO;
import com.rscinema.finalproject.domain.dto.UserDTO;
import com.rscinema.finalproject.domain.entity.user.Role;
import com.rscinema.finalproject.domain.entity.user.User;
import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import com.rscinema.finalproject.domain.mapper.UserMapper;
import com.rscinema.finalproject.repository.UserRepository;
import com.rscinema.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDTO registerUser( RegistrationFormDTO dto) {
        User user = User.builder()
                .role(Role.fromValue(dto.getRole()))
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .age(dto.getAge())
                .build();

        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public UserDTO findUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(
                        "User with id %s not found!",id
                )));
        return UserMapper.toDTO(user);
    }
}
