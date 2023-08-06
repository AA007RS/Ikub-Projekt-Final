package com.rscinema.finalproject.service;

import com.rscinema.finalproject.domain.dto.RegistrationDetailsDTO;
import com.rscinema.finalproject.domain.dto.auth.RegistrationFormDTO;
import com.rscinema.finalproject.domain.dto.UserDTO;
import com.rscinema.finalproject.domain.dto.password.ChangePasswordFormDTO;
import com.rscinema.finalproject.domain.entity.user.User;

import java.util.List;

public interface UserService {

    UserDTO registerUser(RegistrationFormDTO dto);

    User findUserById(Integer id);

    UserDTO findUserByEmail(String email);

    List<UserDTO> findAll();

    List<UserDTO> findAllDeletedAdmins();

    List<UserDTO> findAllDeletedCustomers();

    List<UserDTO> findAllAdmins();

    List<UserDTO> findAllCustomers();

    UserDTO updateDetails(RegistrationDetailsDTO dto);

    UserDTO updatePassword(ChangePasswordFormDTO dto);
}
