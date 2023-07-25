package com.rscinema.finalproject.service;

import com.rscinema.finalproject.domain.dto.GenreDTO;
import com.rscinema.finalproject.domain.entity.genre.Genre;

import java.util.List;

public interface GenreService {


    GenreDTO create(GenreDTO genreDTO);
    Genre findById(Integer id);
    GenreDTO findGenreByName(String genreName);
    List<GenreDTO> findAll();
    GenreDTO update(GenreDTO genreDTO);
    void gentleDelete(Integer id);
    void hardDelete(Integer id);
}
