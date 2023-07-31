package com.rscinema.finalproject.domain.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationFormDTO {

    @NotEmpty(message = "Role must not be empty!")
    private String role;

    @NotEmpty(message = "First Name should not be empty!")
    private String firstName;

    @NotEmpty(message = "Last Name should not be empty!")
    private String lastName;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Oops! email already taken!")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Password not valid")
    private String password;

    @Min(value = 18,message = "Users must be 18 years old and above!")
    private Integer age;
}
