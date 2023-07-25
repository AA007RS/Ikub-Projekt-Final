package com.rscinema.finalproject.repository;

import com.rscinema.finalproject.domain.entity.genre.Genre;
import com.rscinema.finalproject.domain.entity.genre.MovieGenre;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GenreRepository extends JpaRepository<Genre,Integer> {

    Genre findByMovieGenre(MovieGenre movieGenre);

}
