package com.rscinema.finalproject.domain.mapper;

import com.rscinema.finalproject.domain.dto.GenreDTO;
import com.rscinema.finalproject.domain.entity.genre.Genre;

import java.time.LocalDateTime;

public class GenreMapper {

    public static GenreDTO toDTO(Genre genre){

        return GenreDTO.builder()
                .id(genre.getId())
                .name(genre.getMovieGenre())
                .build();
    }

    public static Genre toEntity(GenreDTO genreDTO){
        return Genre.builder()
                .movieGenre(genreDTO.getName().toUpperCase())
                .build();
    }


}
