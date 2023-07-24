package com.rscinema.finalproject.service.impl;

import com.rscinema.finalproject.domain.dto.GenreDTO;
import com.rscinema.finalproject.domain.entity.genre.Genre;
import com.rscinema.finalproject.domain.entity.genre.MovieGenre;
import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import com.rscinema.finalproject.domain.mapper.GenreMapper;
import com.rscinema.finalproject.repository.GenreRepository;
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
    public List<GenreDTO> findAll() {
        return genreRepository.findAll()
                .stream()
                .map(GenreMapper::toDTO)
                .toList();
    }

    @Override
    public GenreDTO update(GenreDTO genreDTO) {
        Genre toFind = findById(genreDTO.getId());
        Genre toReturn = genreRepository.save(GenreMapper.toUpdate(toFind,genreDTO));
        return GenreMapper.toDTO(toReturn);
    }

    @Override
    public void gentleDelete(Integer id) {
        Genre toDelete = findById(id);
        toDelete.setDeleted(true);
        toDelete.setUpdatedAt(LocalDateTime.now());
        genreRepository.save(toDelete);
    }

    @Override
    public void hardDelete(Integer id) {
        genreRepository.deleteById(id);
    }
}
