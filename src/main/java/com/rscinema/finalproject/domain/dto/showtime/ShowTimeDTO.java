package com.rscinema.finalproject.domain.dto.showtime;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowTimeDTO {
    private Integer id;
    private String movie;
    private String room;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalDate endDate;
    private LocalTime endTime;
    private LocalTime readyForNextTime;
    private Double price;
    private Boolean finished;
}
