package com.rscinema.finalproject.domain.dto.showtime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
public class UpdateShowTimeDTO {

    private Integer currentId;
    @NotNull(message = "Please choose a date!")
    private LocalDate startDate;
    @NotNull(message = "Please choose a start time!")
    private LocalTime startTime;
    @NotNull(message = "Please insert a movie")
    private Integer movieId;
}
