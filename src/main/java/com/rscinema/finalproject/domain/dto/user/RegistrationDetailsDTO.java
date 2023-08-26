package com.rscinema.finalproject.domain.dto.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDetailsDTO {

    private Integer id;

    @NotNull(message = "First Name must be filled!")
    @Size(min = 3, message = "First name should have at least 3 characters")
    private String firstName;


    @NotNull(message = "Last Name must be filled!")
    @Size(min = 3, message = "Last name should have at least 3 characters")
    private String lastName;

    @NotEmpty(message = "Gender must not be empty!")
    @NotNull(message = "Role must be filled!")
    private String gender;

    @NotNull(message = "Age field must be filled")
    @Min(value = 18, message = "Users must be 18 years old and above!")
    private Integer age;

    @NotNull(message = "Phone number field must be filled!")
    @Size(min = 10, message = "Phone number must have at least 10 digits!")
    private String phoneNumber;
}
