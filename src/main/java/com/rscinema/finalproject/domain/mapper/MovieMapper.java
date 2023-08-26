package com.rscinema.finalproject.domain.mapper;

import com.rscinema.finalproject.domain.dto.movie.MovieDTO;
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
                .id(movie.getId())
                .title(movie.getTitle())
                .director(movie.getDirector())
                .yearReleased(movie.getYearReleased())
                .length(movie.getLength())
                .description(movie.getDescription())
                .genreId(movie.getGenre().getId())
                .build();
    }

    public static Movie toUpdate(Movie e, MovieDTO d) {
        e.setTitle(d.getTitle());
        e.setDirector(d.getDirector());
        e.setLength(d.getLength());
        e.setDescription(d.getDescription());
        e.setYearReleased(d.getYearReleased());
        return e;

    }
}
