package com.rscinema.finalproject.service;

import com.rscinema.finalproject.domain.dto.GenreDTO;
import com.rscinema.finalproject.domain.dto.MovieDTO;
import com.rscinema.finalproject.domain.entity.Movie;

import java.util.List;

public interface MovieService {

    MovieDTO create(MovieDTO movieDTO);
    Movie findById(Integer id);
    List<MovieDTO> findAll();
    List<MovieDTO> findAllByGenre(GenreDTO genreDTO);
    MovieDTO update(MovieDTO movieDTO);
    void softDelete(Integer id);
    void hardDelete(Integer id);
}
