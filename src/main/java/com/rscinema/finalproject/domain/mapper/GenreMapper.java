package com.rscinema.finalproject.domain.mapper;

import com.rscinema.finalproject.domain.dto.GenreDTO;
import com.rscinema.finalproject.domain.entity.genre.Genre;
import com.rscinema.finalproject.domain.entity.genre.MovieGenre;

public class GenreMapper {

    public static GenreDTO toDTO(Genre genre){
        return GenreDTO.builder()
                .id(genre.getId())
                .name(genre.getMovieGenre().getValue())
                .build();
    }

    public static Genre toEntity(GenreDTO genreDTO){
        MovieGenre movieGenre = MovieGenre.fromValue(genreDTO.getName());
        return Genre.builder()
                .movieGenre(movieGenre)
                .build();
    }
}
