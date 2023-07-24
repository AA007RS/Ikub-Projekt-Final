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
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreServiceImpl genreService;

    @PostMapping()
    public ResponseEntity<GenreDTO> create(@RequestBody GenreDTO genreDTO){
        return ResponseEntity.ok(genreService.create(genreDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDTO> findById(@PathVariable("id")Integer id){
        GenreDTO toReturn = GenreMapper.toDTO(genreService.findById(id));
        return ResponseEntity.ok(toReturn);
    }

    @GetMapping()
    public ResponseEntity<List<GenreDTO>> findAll(){
        return ResponseEntity.ok(genreService.findAll());
    }

    @PutMapping()
    public ResponseEntity<GenreDTO> update(@RequestBody GenreDTO genreDTO){
        return ResponseEntity.ok(genreService.update(genreDTO));
    }

    @PutMapping("/gd/{id}")
    public ResponseEntity<String> gentleDeleteById(@PathVariable("id")Integer id){
        genreService.gentleDelete(id);
        return new ResponseEntity<>(String.format("Genre with id %s gently deleted!",id), HttpStatus.OK);
    }
    @DeleteMapping("/hd/{id}")
    public ResponseEntity<String> hardDeleteById(@PathVariable("id")Integer id){
        genreService.hardDelete(id);
        return new ResponseEntity<>(String.format("Genre with id %s hardly deleted!",id), HttpStatus.OK);
    }

}
