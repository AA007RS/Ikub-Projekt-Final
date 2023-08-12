package com.rscinema.finalproject.controller;

import com.rscinema.finalproject.domain.dto.movie.MovieDTO;
import com.rscinema.finalproject.domain.dto.movie.MovieSearchByAdminDTO;
import com.rscinema.finalproject.domain.entity.Movie;
import com.rscinema.finalproject.domain.mapper.MovieMapper;
import com.rscinema.finalproject.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/existing/{id}")
    public ResponseEntity<MovieDTO> findExistingById(@PathVariable("id")Integer id){
        return ResponseEntity.ok(movieService.findExistingById(id));
    }

    @GetMapping()
    public ResponseEntity<List<MovieDTO>> findAll() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/admin/search")
    public ResponseEntity<List<MovieDTO>> searchWithAdmin(@RequestBody MovieSearchByAdminDTO dto){
        return ResponseEntity.ok(movieService.searchMoviesAdmin(dto));
    }

    @GetMapping("/user/search")
    public ResponseEntity<List<MovieDTO>> searchWithCustomer(@RequestParam String title){
        return ResponseEntity.ok(movieService.searchMoviesUser(title));
    }

    @GetMapping("/byGenre/{id}")
    public ResponseEntity<List<MovieDTO>> findAllExistingByGenreId(@PathVariable("id") Integer id){
        return ResponseEntity.ok(movieService.findAllExistingByGenreId(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable("id") Integer id,
                                           @RequestBody MovieDTO movieDTO) {
        return ResponseEntity.ok(movieService.update(id,movieDTO));
    }

    @PutMapping("/sd/{id}")
    public ResponseEntity<String> softDelete(@PathVariable("id") Integer id) {
        movieService.softDelete(id);
        return new ResponseEntity<>(String.format("Movie with id %s is soft deleted!", id),
                HttpStatus.OK);
    }


    @DeleteMapping("/hd/{id}")
    public ResponseEntity<String> hardDelete(@PathVariable("id") Integer id) {
        movieService.hardDelete(id);
        return new ResponseEntity<>(String.format("Movie with id %s hardly deleted!", id),
                HttpStatus.OK);
    }

}
