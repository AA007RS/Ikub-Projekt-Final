package com.rscinema.finalproject.domain.dto.auth;

import jakarta.validation.constraints.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationFormDTO {


    @NotEmpty(message = "First Name should not be empty!")
    @NotNull(message = "First Name must be filled!")
    @Size(min = 3, message = "First name should have at least 3 characters")
    private String firstName;

    @NotEmpty(message = "Last Name should not be empty!")
    @NotNull(message = "Last Name must be filled!")
    @Size(min = 3, message = "Last name should have at least 3 characters")
    private String lastName;

    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Email not valid!")
    @NotNull(message = "Email must be filled!")
    private String email;

    @NotNull(message = "Password field must be filled")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Password not valid")
    private String password;

    @NotNull(message = "Age field must be filled")
    @Min(value = 18, message = "Users must be 18 years old and above!")
    private Integer age;
}
