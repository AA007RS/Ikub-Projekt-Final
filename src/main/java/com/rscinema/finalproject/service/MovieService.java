package com.rscinema.finalproject.service;

import com.rscinema.finalproject.domain.dto.movie.MovieDTO;
import com.rscinema.finalproject.domain.dto.movie.MovieSearchByAdminDTO;
import com.rscinema.finalproject.domain.entity.Movie;

import java.util.List;

public interface MovieService {

    MovieDTO create(MovieDTO movieDTO);
    Movie findById(Integer id);
    MovieDTO findExistingById(Integer id);
    public List<MovieDTO> findAll();
    List<MovieDTO> findAllExistingByGenreId(Integer id);
    List<MovieDTO> searchMoviesAdmin(MovieSearchByAdminDTO dto);
    List<MovieDTO> searchMoviesUser(String name);
    MovieDTO update(Integer id,MovieDTO movieDTO);
    void softDelete(Integer id);
    void hardDelete(Integer id);
    void restore(Integer id);
}
