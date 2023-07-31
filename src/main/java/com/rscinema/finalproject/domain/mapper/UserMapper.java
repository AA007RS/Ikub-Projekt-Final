package com.rscinema.finalproject.domain.mapper;

import com.rscinema.finalproject.domain.dto.UserDTO;
import com.rscinema.finalproject.domain.entity.user.Gender;
import com.rscinema.finalproject.domain.entity.user.Role;
import com.rscinema.finalproject.domain.entity.user.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .password(user.getPassword())
                .age(user.getAge())
                .role(user.getRole().getValue())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public static User toEntity(UserDTO userDTO) {
        return User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .gender(Gender.fromValue(userDTO.getGender()))
                .role(Role.fromValue(userDTO.getRole()))
                .phoneNumber(userDTO.getPhoneNumber())
                .build();
    }

    public static User toUpdate(User u, UserDTO d) {
        u.setFirstName(d.getFirstName());
        u.setLastName(d.getLastName());
        u.setGender(Gender.fromValue(d.getGender()));
        u.setPassword(d.getPassword());
        u.setAge(d.getAge());
        u.setPhoneNumber(d.getPhoneNumber());

        return u;
    }
}
