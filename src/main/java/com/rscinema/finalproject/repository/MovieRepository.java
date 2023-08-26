package com.rscinema.finalproject.repository;

import com.rscinema.finalproject.domain.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    @Query("SELECT m FROM Movie m WHERE " +
            "(m.title ILIKE concat(:title,'%') OR :title IS NULL) AND " +
            "(m.deleted = :deleted OR :deleted IS NULL) ORDER BY m.yearReleased desc ")
    List<Movie> searchMovies(@Param("title") String title, @Param("deleted") Boolean deleted);

    List<Movie> findAllByTitleContainingIgnoreCaseAndDeletedOrderByYearReleasedDesc(String title, Boolean status);

    Optional<Movie> findByIdAndDeletedIsFalse(Integer id);

    Optional<Movie> findByTitleIgnoreCase(String title);

    List<Movie> findAllByGenre_IdAndGenre_DeletedIsFalseAndDeletedIsFalse(Integer id);
}
