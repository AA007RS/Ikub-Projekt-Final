package com.rscinema.finalproject.domain.dto.showtime;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class ShowTimeSearchDTO {

    private Integer movieId;
    private Integer roomId;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;
    private Boolean deleted;
    private Boolean finished;
}
