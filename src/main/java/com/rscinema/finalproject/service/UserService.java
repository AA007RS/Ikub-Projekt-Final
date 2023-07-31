package com.rscinema.finalproject.service;

import com.rscinema.finalproject.domain.dto.RegistrationFormDTO;
import com.rscinema.finalproject.domain.dto.UserDTO;

public interface UserService {

    UserDTO registerUser(RegistrationFormDTO dto);
    UserDTO findUserById(Integer id);
}
