package com.rscinema.finalproject.domain.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSearchDTO {
    private String email;
    private String gender;
    private Boolean deleted;
}
