package com.rscinema.finalproject.domain.mapper;

import com.rscinema.finalproject.domain.dto.MovieDTO;
import com.rscinema.finalproject.domain.entity.Movie;

public class MovieMapper {

    public static Movie toEntity(MovieDTO movieDTO) {

        return Movie.builder()
                .title(movieDTO.getTitle())
                .director(movieDTO.getDirector())
                .yearReleased(movieDTO.getYearReleased())
                .length(movieDTO.getLength())
                .description(movieDTO.getDescription())
                .build();

    }

    public static MovieDTO toDTO(Movie movie) {
        return MovieDTO.builder()
                .title(movie.getTitle())
                .director(movie.getDirector())
                .yearReleased(movie.getYearReleased())
                .length(movie.getLength())
                .description(movie.getDescription())
                .genre(movie.getGenre().getMovieGenre().getValue())
                .build();
    }
}
