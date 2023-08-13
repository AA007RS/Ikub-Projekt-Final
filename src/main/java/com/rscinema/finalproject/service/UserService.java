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
    User findExistingById(Integer id);
    List<UserDTO> findAllDeletedCustomers();
    List<UserDTO> findAllExistingCustomers();
    List<UserDTO> search(UserSearchDTO dto);
    UserDTO updateDetails(RegistrationDetailsDTO dto);
    String updatePassword(ChangePasswordFormDTO dto);
    String setPasswordDefault(Integer id);
    UserDTO seeUserDetails(Integer id);
    String deleteAccount(Integer id);
}
