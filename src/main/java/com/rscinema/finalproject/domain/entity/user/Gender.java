package com.rscinema.finalproject.domain.entity.user;

import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum Gender {

    MALE("MALE"),
    FEMALE("FEMALE"),
    PNTS("PREFER NOT TO SAY");

    private final String value;

    public static Gender fromValue(String value){
        return Arrays.stream(Gender.values())
                .filter(g->g.getValue().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException(String.format(
                        "Gender %s not found!",value
                )));
    }
}
