package com.rscinema.finalproject.repository;

import com.rscinema.finalproject.domain.entity.Movie;
import com.rscinema.finalproject.domain.entity.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    List<Movie> findAllByGenreAndDeletedIsFalse(Genre genre);
}
