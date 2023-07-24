package com.rscinema.finalproject.repository;

import com.rscinema.finalproject.domain.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Integer> {
}
