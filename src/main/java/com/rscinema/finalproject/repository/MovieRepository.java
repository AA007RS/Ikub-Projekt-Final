package com.rscinema.finalproject.repository;

import com.rscinema.finalproject.domain.entity.Movie;
import com.rscinema.finalproject.domain.entity.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    Optional<Movie> findByTitleIgnoreCase(String title);
    List<Movie> findAllByGenreAndDeletedIsFalse(Genre genre);
}
