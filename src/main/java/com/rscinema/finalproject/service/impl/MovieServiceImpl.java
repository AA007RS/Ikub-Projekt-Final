package com.rscinema.finalproject.service.impl;

import com.rscinema.finalproject.domain.dto.movie.MovieDTO;
import com.rscinema.finalproject.domain.dto.movie.MovieSearchByAdminDTO;
import com.rscinema.finalproject.domain.entity.Movie;
import com.rscinema.finalproject.domain.entity.Genre;
import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import com.rscinema.finalproject.domain.mapper.MovieMapper;
import com.rscinema.finalproject.repository.GenreRepository;
import com.rscinema.finalproject.repository.MovieRepository;
import com.rscinema.finalproject.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    //nje film krijohet vetem nga nje admin

    @Override
    public MovieDTO create(MovieDTO movieDTO) {
        Genre genre = genreRepository.findById(movieDTO.getGenreId())
                .orElseThrow(()-> new ResourceNotFoundException(String.format(
                        "Genre with id %s not found!",movieDTO.getGenreId()
                )));

        Movie toSave = MovieMapper.toEntity(movieDTO);
        toSave.setGenre(genre);

        return MovieMapper.toDTO(movieRepository.save(toSave));

    }

    //e akseson vetem admini
    @Override
    public Movie findById(Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException(String
                        .format("Movie with id %s not found!",id)));

    }

    //admini kerkon filma sipas 2 fields: name dhe deleted
    @Override
    public List<MovieDTO> searchMoviesAdmin(MovieSearchByAdminDTO dto) {

        List<Movie> movies = movieRepository.searchMovies(dto.getTitle(),dto.getDeleted());
//        if(dto.getName()==null && dto.getDeleted()==null){
//            return findAll();
//        } else if (dto.getName()==null ) {
//            movies = movieRepository.findAllByDeleted(dto.getDeleted());
//        }else if (dto.getDeleted()==null) {
//            movies = movieRepository.findAllByTitleContainingIgnoreCase(dto.getName());
//        }else{
//            movies = movieRepository.findAllByTitleContainingIgnoreCaseAndDeleted(dto.getName(),dto.getDeleted());
//        }
        return movies.stream()
                .map(MovieMapper::toDTO)
                .toList();
    }

    // ndihmese tek searchi i adminit
    @Override
    public List<MovieDTO> findAll() {
        return movieRepository.findAll().stream()
                .map(MovieMapper::toDTO)
                .toList();
    }

    // vetem admini
    @Override
    public MovieDTO update(Integer id, MovieDTO movieDTO) {
        Movie toFind = findById(movieDTO.getId());

        Genre genreToFind = genreRepository.findById(movieDTO.getGenreId()).
                orElseThrow(() -> new ResourceNotFoundException(String.format(
                        "Genre with id %s not found!",movieDTO.getGenreId()
                )));
        toFind.setGenre(genreToFind);

        Movie toReturn = MovieMapper.toUpdate(toFind,movieDTO);


        return MovieMapper.toDTO(movieRepository.save(toReturn));
    }

    //admini
    @Override
    public void softDelete(Integer id) {
        Movie toDelete = findById(id);
        toDelete.setDeleted(true);
        movieRepository.save(toDelete);
    }

    //admini
    @Override
    public void restore(Integer id) {
        Movie movie = findById(id);
        movie.setDeleted(false);
        movieRepository.save(movie);
    }

    @Override
    public List<MovieDTO> searchMoviesUser(String name) {
        return movieRepository.findAllByTitleContainingIgnoreCaseAndDeletedOrderByYearReleasedDesc(name,false)
                .stream()
                .map(MovieMapper::toDTO)
                .toList();
    }


    //e akseson useri sepse ai sheh vetem filmat ekzistues
    @Override
    public MovieDTO findExistingById(Integer id) {
        return MovieMapper.toDTO(movieRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(()-> new ResourceNotFoundException(String.format(
                        "Movie with id %s not found!",id
                ))));
    }


    // e akseson useri
    //shikon te gjithe filmat nga nje zhaner i caktuar
    @Override
    public List<MovieDTO> findAllExistingByGenreId(Integer id) {

        Genre genreToFind = genreRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(
                        "Genre with id %s not found!",id
                ))
        );
        return movieRepository.findAllByGenre_IdAndGenre_DeletedIsFalseAndDeletedIsFalse(id).stream()
                .map(MovieMapper::toDTO)
                .toList();
    }
}
