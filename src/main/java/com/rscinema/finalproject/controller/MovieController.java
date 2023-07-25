package com.rscinema.finalproject.controller;

import com.rscinema.finalproject.domain.dto.GenreDTO;
import com.rscinema.finalproject.domain.dto.MovieDTO;
import com.rscinema.finalproject.domain.entity.Movie;
import com.rscinema.finalproject.domain.mapper.MovieMapper;
import com.rscinema.finalproject.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @PostMapping()
    public ResponseEntity<MovieDTO> create(@RequestBody MovieDTO movieDTO) {
        return ResponseEntity.ok(movieService.create(movieDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable("id") Integer id) {
        Movie toReturn = movieService.findById(id);
        return ResponseEntity.ok(MovieMapper.toDTO(toReturn));
    }

    @GetMapping()
    public ResponseEntity<List<MovieDTO>> findAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/byGenre")
    public ResponseEntity<List<MovieDTO>> findAllByGenre(@RequestBody GenreDTO genreDTO){
        return ResponseEntity.ok(movieService.findAllByGenre(genreDTO));
    }
    @PutMapping()
    public ResponseEntity<MovieDTO> update(@RequestBody MovieDTO movieDTO) {
        return ResponseEntity.ok(movieService.update(movieDTO));
    }

    @PutMapping("/sd/{id}")
    public ResponseEntity<String> softDelete(@PathVariable("id") Integer id) {
        movieService.softDelete(id);
        return new ResponseEntity<>(String.format("Movie with id %s is softly deleted!", id),
                HttpStatus.OK);
    }


    @DeleteMapping("/hd/{id}")
    public ResponseEntity<String> hardDelete(@PathVariable("id") Integer id) {
        movieService.hardDelete(id);
        return new ResponseEntity<>(String.format("Movie with id %s hardly deleted!", id),
                HttpStatus.OK);
    }

}
