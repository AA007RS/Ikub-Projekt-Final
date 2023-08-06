package com.rscinema.finalproject.domain.dto.password;

import jakarta.validation.constraints.Pattern;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordFormDTO {

    private Integer id;
    private String oldPassword;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "Password not valid")
    private String newPassword;
    private String renewPassword;
}
