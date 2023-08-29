package com.rscinema.finalproject.controller;

import com.rscinema.finalproject.domain.dto.GenreDTO;
import com.rscinema.finalproject.domain.mapper.GenreMapper;
import com.rscinema.finalproject.service.impl.GenreServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/genres/admin")
public class GenreController {

    private final GenreServiceImpl genreService;

    @GetMapping()
    public ResponseEntity<List<GenreDTO>> findAllPresent() {
        return ResponseEntity.ok(genreService.findAllPresent());
    }


    @PutMapping("/sd/{id}")
    public ResponseEntity<String> softDelete(@PathVariable("id") Integer id) {
        genreService.softDelete(id);
        return new ResponseEntity<>(String.format("Genre with id %s gently deleted!", id), HttpStatus.OK);
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<String> restore(@PathVariable("id") Integer id) {
        genreService.restore(id);
        return new ResponseEntity<>(String.format("Genre with id %s is restored!", id), HttpStatus.OK);
    }

}
