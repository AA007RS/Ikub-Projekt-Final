package com.rscinema.finalproject.domain.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GenreDTO {

    private Integer id;
    @NotNull
    @NotEmpty
    private String name;

}
