package com.rscinema.finalproject.repository;

import com.rscinema.finalproject.domain.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
    List<Movie> findAllByDeleted(Boolean status);
    List<Movie> findAllByTitleContainingIgnoreCase(String name);
    List<Movie> findAllByTitleContainingIgnoreCaseAndDeleted(String title, Boolean status);
    Optional<Movie> findByIdAndDeletedIsFalse(Integer id);
    Optional<Movie> findByTitleIgnoreCase(String title);
    List<Movie> findAllByGenre_IdAndGenre_DeletedIsFalseAndDeletedIsFalse(Integer id);
}
