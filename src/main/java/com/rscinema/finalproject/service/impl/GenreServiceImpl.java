package com.rscinema.finalproject.service.impl;

import com.rscinema.finalproject.domain.dto.GenreDTO;
import com.rscinema.finalproject.domain.entity.Movie;
import com.rscinema.finalproject.domain.entity.genre.Genre;
import com.rscinema.finalproject.domain.exception.PresentException;
import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import com.rscinema.finalproject.domain.mapper.GenreMapper;
import com.rscinema.finalproject.repository.GenreRepository;
import com.rscinema.finalproject.repository.MovieRepository;
import com.rscinema.finalproject.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public GenreDTO create(GenreDTO genreDTO) {
        if(genreRepository.findByMovieGenreIgnoreCase(genreDTO.getName()).isPresent()){
            throw new PresentException(String.format("Genre %s is present!",genreDTO.getName().toUpperCase()));
        }
        Genre genre = GenreMapper.toEntity(genreDTO);
        genreRepository.save(genre);
        return GenreMapper.toDTO(genre);
    }

    @Override
    public Genre findById(Integer id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String
                        .format("Genre with id %s not found!",id)));
    }


    @Override
    public List<GenreDTO> findAllPresent() {
        return genreRepository.findAllByDeletedFalse()
                .stream()
                .map(GenreMapper::toDTO)
                .toList();
    }

    // ketu filmat qe kane kete zhaner do te behen dhe ata soft deleted
    @Override
    public void softDelete(Integer id) {
        Genre toDelete = findById(id);
        toDelete.setDeleted(true);
        for (Movie movie: toDelete.getMovies()){
            movie.setDeleted(true);
        }
        genreRepository.save(toDelete);
    }

    //ktu filmat qe jane soft deleted do e duhen te behn restore manualisht
    @Override
    public void restore(Integer id) {
        Genre toRestore = findById(id);
        toRestore.setDeleted(false);
        genreRepository.save(toRestore);
    }

}
