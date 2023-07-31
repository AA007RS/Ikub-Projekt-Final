package com.rscinema.finalproject.domain.entity.genre;

import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
@Getter
@AllArgsConstructor
public enum MovieGenre {

    ACTION("ACTION"), COMEDY("COMEDY"),
    ANIMATION("ANIMATION"), HORROR("HORROR"),
    DRAMA("DRAMA"), ROMANCE("ROMANCE"),
    THRILLER("THRILLER"), SCIENCE_FICTION("SCIENCE FICTION"),
    FANTASY("FANTASY"), ADVENTURE("ADVENTURE");

    private final String value;

    public static MovieGenre fromValue(String movieGenre){
        return Arrays.stream(MovieGenre.values()).filter(r->r.value.equalsIgnoreCase(movieGenre))
                .findFirst()
                .orElseThrow(()->new ResourceNotFoundException(String
                        .format("Genre %s not found",movieGenre)));
    }
}
