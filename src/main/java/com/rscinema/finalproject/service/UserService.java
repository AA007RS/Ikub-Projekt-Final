package com.rscinema.finalproject.service;

import com.rscinema.finalproject.domain.dto.auth.RegistrationFormDTO;
import com.rscinema.finalproject.domain.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO registerUser(RegistrationFormDTO dto);
    UserDTO findUserById(Integer id);
    List<UserDTO> findAll();
    List<UserDTO> findAllDeletedAdmins();
    List<UserDTO> findAllDeletedCustomers();
    List<UserDTO> findAllAdmins();
    List<UserDTO> findAllCustomers();
}
