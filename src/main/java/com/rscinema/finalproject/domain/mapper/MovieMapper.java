package com.rscinema.finalproject.domain.mapper;

import com.rscinema.finalproject.domain.dto.MovieDTO;
import com.rscinema.finalproject.domain.entity.Movie;

import java.time.LocalDateTime;

public class MovieMapper {

    public static Movie toEntity(MovieDTO movieDTO) {

        return Movie.builder()
                .title(movieDTO.getTitle())
                .director(movieDTO.getDirector())
                .yearReleased(movieDTO.getYearReleased())
                .length(movieDTO.getLength())
                .description(movieDTO.getDescription())
                .genre(GenreMapper.toEntity(movieDTO.getGenreDTO()))
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
                .genreDTO(GenreMapper.toDTO(movie.getGenre()))
                .build();
    }

    public static Movie toUpdate(Movie e, MovieDTO d){
        e.setTitle(d.getTitle());
        e.setDirector(d.getDirector());
        e.setLength(d.getLength());
        e.setDescription(d.getDescription());
        e.setYearReleased(d.getYearReleased());
        e.setGenre(GenreMapper.toEntity(d.getGenreDTO()));
        return e;

    }
}
