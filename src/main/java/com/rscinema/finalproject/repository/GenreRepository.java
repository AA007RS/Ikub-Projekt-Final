package com.rscinema.finalproject.repository;

import com.rscinema.finalproject.domain.entity.genre.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


public interface GenreRepository extends JpaRepository<Genre,Integer> {

    Optional<Genre> findByMovieGenreIgnoreCase(String genre);
    List<Genre> findAllByDeletedFalse();

}
