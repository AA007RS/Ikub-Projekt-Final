package com.rscinema.finalproject.domain.dto.showtime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private LocalTime readyForNextTime;
    private Double price;
}
