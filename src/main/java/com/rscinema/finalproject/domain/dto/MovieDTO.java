package com.rscinema.finalproject.domain.dto;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieDTO {
    private Integer id;
    private String title;
    private String director;
    private Integer yearReleased;
    private Integer length;
    private String description;
    private GenreDTO genreDTO;
}
