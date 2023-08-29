package com.rscinema.finalproject.controller;

import com.rscinema.finalproject.domain.dto.movie.MovieDTO;
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

    @PostMapping("/admin")
    public ResponseEntity<MovieDTO> create(@RequestBody MovieDTO movieDTO) {
        return ResponseEntity.ok(movieService.create(movieDTO));
    }

    @GetMapping("/admin/search")
    public ResponseEntity<List<MovieDTO>> searchWithAdmin(@RequestParam(required = false)String title,
                                                          @RequestParam(required = false)boolean deleted) {
        return ResponseEntity.ok(movieService.searchMoviesAdmin(title,deleted));
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable("id") Integer id,
                                           @RequestBody MovieDTO movieDTO) {
        movieDTO.setId(id);
        return ResponseEntity.ok(movieService.update(id, movieDTO));
    }

    @PutMapping("/admin/sd/{id}")
    public ResponseEntity<String> softDelete(@PathVariable("id") Integer id) {
        movieService.softDelete(id);
        return new ResponseEntity<>(String.format("Movie with id %s is soft deleted!", id),
                HttpStatus.OK);
    }

    @PutMapping("/admin/restore/{id}")
    public ResponseEntity<String> restore(@PathVariable("id") Integer id) {
        movieService.restore(id);
        return new ResponseEntity<>(String.format("Movie with id %s is restored!", id),
                HttpStatus.OK);
    }

    @GetMapping("/customer/search")
    public ResponseEntity<List<MovieDTO>> searchWithCustomer(@RequestParam String title) {
        return ResponseEntity.ok(movieService.searchMoviesUser(title));
    }

    @GetMapping("/byGenre/{id}")
    public ResponseEntity<List<MovieDTO>> findAllExistingByGenreId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(movieService.findAllExistingByGenreId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> findById(@PathVariable("id") Integer id) {
        Movie toReturn = movieService.findById(id);
        return ResponseEntity.ok(MovieMapper.toDTO(toReturn));
    }

}
