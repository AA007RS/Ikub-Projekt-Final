package com.rscinema.finalproject.service.impl;

import com.rscinema.finalproject.domain.dto.GenreDTO;
import com.rscinema.finalproject.domain.dto.MovieDTO;
import com.rscinema.finalproject.domain.entity.Movie;
import com.rscinema.finalproject.domain.entity.genre.Genre;
import com.rscinema.finalproject.domain.entity.genre.MovieGenre;
import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import com.rscinema.finalproject.domain.mapper.MovieMapper;
import com.rscinema.finalproject.repository.GenreRepository;
import com.rscinema.finalproject.repository.MovieRepository;
import com.rscinema.finalproject.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    @Override
    public MovieDTO create(MovieDTO movieDTO) {
        MovieGenre movieGenre = MovieGenre.fromValue(movieDTO.getGenreDTO().getName());
        Genre genre = genreRepository.findByMovieGenre(movieGenre);
        if(genre!=null){
//            genre = Genre.builder().movieGenre(movieGenre).build();
//            genreRepository.save(genre);
       }
        Movie toSave = MovieMapper.toEntity(movieDTO);
      //  toSave.setGenre(genre);

        return MovieMapper.toDTO(movieRepository.save(toSave));

    }

    @Override
    public Movie findById(Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(String
                        .format("Movie with id %s not found!",id)));

    }

    @Override
    public List<MovieDTO> findAll() {
        return movieRepository.findAll().stream()
                .map(MovieMapper::toDTO)
                .toList();
    }

    @Override
    public List<MovieDTO> findAllByGenre(GenreDTO genreDTO) {
        MovieGenre movieGenre = MovieGenre.fromValue(genreDTO.getName());
        Genre genreToFind = genreRepository.findByMovieGenre(movieGenre);
        return movieRepository.findAllByGenreAndDeletedIsFalse(genreToFind).stream()
                .map(MovieMapper::toDTO)
                .toList();
    }

    @Override
    public MovieDTO update(MovieDTO movieDTO) {
        Movie toFind = findById(movieDTO.getId());

        Genre genreToFind = genreRepository.findByMovieGenre(MovieGenre.fromValue(movieDTO.getGenreDTO().getName()));
        genreToFind.setUpdatedAt(LocalDateTime.now());

        Movie toReturn = MovieMapper.toUpdate(toFind,movieDTO);
        toReturn.setGenre(genreToFind);

        return MovieMapper.toDTO(movieRepository.save(toReturn));
    }

    @Override
    public void softDelete(Integer id) {
        Movie toDelete = findById(id);
        toDelete.setDeleted(true);
        movieRepository.save(toDelete);
    }

    @Override
    public void hardDelete(Integer id) {
        Movie toDelete = findById(id);
        movieRepository.delete(toDelete);
    }

}
