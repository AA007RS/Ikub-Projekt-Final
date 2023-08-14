package com.rscinema.finalproject.service;

import com.rscinema.finalproject.domain.dto.GenreDTO;
import com.rscinema.finalproject.domain.entity.Genre;

import java.util.List;

public interface GenreService {


    GenreDTO create(GenreDTO genreDTO);
    Genre findById(Integer id);
    List<GenreDTO> findAllPresent();
    void softDelete(Integer id);
    void restore(Integer id);

}
