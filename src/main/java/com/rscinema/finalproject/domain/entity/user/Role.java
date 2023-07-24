package com.rscinema.finalproject.domain.entity.user;

import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum Role {

    USER("CUSTOMER"),
    ADMIN("ADMIN");
    private final String value;

    public static Role fromValue(String role){
        return Arrays.stream(Role.values())
                .filter(r->r.value.equalsIgnoreCase(role))
                .findFirst()
                .orElseThrow(()->new ResourceNotFoundException(String
                        .format("Role %s not found!",role)));
    }

    public String getValue(){
      return value;
    }
}
