package com.rscinema.finalproject.service;

import com.rscinema.finalproject.domain.dto.user.RegistrationDetailsDTO;
import com.rscinema.finalproject.domain.dto.auth.RegistrationFormDTO;
import com.rscinema.finalproject.domain.dto.user.UserDTO;
import com.rscinema.finalproject.domain.dto.password.ChangePasswordFormDTO;
import com.rscinema.finalproject.domain.dto.user.UserSearchDTO;
import com.rscinema.finalproject.domain.entity.user.User;

import java.util.List;

public interface UserService {

    UserDTO registerUser(RegistrationFormDTO dto);

    User findUserById(Integer id);

    UserDTO findUserByEmail(String email);

    List<UserDTO> findAllDeletedCustomers();

    List<UserDTO> findAllCustomers();
    List<UserDTO> search(UserSearchDTO dto);

    UserDTO updateDetails(RegistrationDetailsDTO dto);

    UserDTO updatePassword(ChangePasswordFormDTO dto);
}
