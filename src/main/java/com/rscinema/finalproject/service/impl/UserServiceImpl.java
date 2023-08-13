package com.rscinema.finalproject.service.impl;

import com.rscinema.finalproject.configuration.SecurityUtils;
import com.rscinema.finalproject.domain.dto.user.RegistrationDetailsDTO;
import com.rscinema.finalproject.domain.dto.auth.RegistrationFormDTO;
import com.rscinema.finalproject.domain.dto.user.UserDTO;
import com.rscinema.finalproject.domain.dto.password.ChangePasswordFormDTO;
import com.rscinema.finalproject.domain.dto.user.UserSearchDTO;
import com.rscinema.finalproject.domain.entity.user.Gender;
import com.rscinema.finalproject.domain.entity.user.Role;
import com.rscinema.finalproject.domain.entity.user.User;
import com.rscinema.finalproject.domain.exception.NoContentException;
import com.rscinema.finalproject.domain.exception.PasswordException;
import com.rscinema.finalproject.domain.exception.PresentException;
import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import com.rscinema.finalproject.domain.mapper.UserMapper;
import com.rscinema.finalproject.repository.UserRepository;
import com.rscinema.finalproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO registerUser(RegistrationFormDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new PresentException("Email already taken!");
        }
        User user = User.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .age(dto.getAge())
                .role(Role.CUSTOMER)
                .build();

        return UserMapper.toDTO(userRepository.save(user));
    }

    @Override
    public User findUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(
                        "User with id %s not found!", id
                )));
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(
                        "User with email %s not found!", email
                )));
        return UserMapper.toDTO(user);
    }

    @Override
    public List<UserDTO> findAllDeletedCustomers() {
        List<User> customers = userRepository.findAllByRoleAndDeletedTrue(Role.fromValue("CUSTOMER"));
        if (customers.isEmpty()) {
            throw new NoContentException("No content for this page!");
        }
        return customers
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    @Override
    public List<UserDTO> findAllCustomers() {
        return userRepository.findAllByRoleAndDeletedFalse(Role.fromValue("CUSTOMER")).stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    @Override
    public List<UserDTO> search(UserSearchDTO dto) {
        List<User> customers;

        if (dto.getEmail() == null && dto.getGender() == null) {
            return findAllCustomers();
        } else if (dto.getEmail() == null) {
            customers = userRepository.findAllByGenderAndRole(Gender.fromValue(dto.getGender()),Role.CUSTOMER);
        } else if (dto.getGender() == null) {
            customers = userRepository.findByEmailContainingIgnoreCaseAndRole(dto.getEmail(), Role.CUSTOMER);
        } else {
            customers = userRepository.findByEmailContainingIgnoreCaseAndGenderAndRole(
                    dto.getEmail(), Gender.fromValue(dto.getGender()), Role.CUSTOMER);
        }
        return customers.stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    @Override
    public UserDTO updateDetails(RegistrationDetailsDTO dto) {
        if (!(Objects.equals(SecurityUtils.getLoggedUserId(), dto.getId()))) {
            throw new ResourceNotFoundException("You can update only your own details!");
        }

        User toUpdate = findUserById(dto.getId());

        toUpdate.setFirstName(dto.getFirstName());
        toUpdate.setLastName(dto.getLastName());
        toUpdate.setGender(Gender.fromValue(dto.getGender()));
        toUpdate.setAge(dto.getAge());
        toUpdate.setPhoneNumber(dto.getPhoneNumber());

        return UserMapper.toDTO(userRepository.save(toUpdate));
    }

    @Override
    public UserDTO updatePassword(ChangePasswordFormDTO dto) {
        if (!(Objects.equals(SecurityUtils.getLoggedUserId(), dto.getId()))) {
            throw new ResourceNotFoundException("You can update only your own details!");
        }
        User toUpdate = findUserById(dto.getId());
        if (!passwordEncoder.matches(dto.getOldPassword(),
                toUpdate.getPassword())) {
            throw new PasswordException(
                    "Old password is not correct!"
            );
        } else if (passwordEncoder.matches(dto.getNewPassword(),
                toUpdate.getPassword())) {
            throw new PasswordException(
                    "Old password and new password must not be the same!"
            );
        } else if (!dto.getNewPassword().equals(dto.getRenewPassword())) {
            throw new PasswordException(
                    "New password fields must match!"
            );
        }
        toUpdate.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        return UserMapper.toDTO(userRepository.save(toUpdate));
    }
}
