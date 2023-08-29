package com.rscinema.finalproject.service;

import com.rscinema.finalproject.domain.dto.movie.MovieDTO;
import com.rscinema.finalproject.domain.entity.Movie;

import java.util.List;

public interface MovieService {

    MovieDTO create(MovieDTO movieDTO);

    Movie findById(Integer id);

    List<MovieDTO> findAllExistingByGenreId(Integer id);

    List<MovieDTO> searchMoviesAdmin(String title, boolean deleted);

    List<MovieDTO> searchMoviesUser(String name);

    MovieDTO update(Integer id, MovieDTO movieDTO);

    void softDelete(Integer id);

    void restore(Integer id);
}
