package com.rscinema.finalproject.domain.dto.movie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieSearchByAdminDTO {
    private String name;
    private Boolean deleted;
}
