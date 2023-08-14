package com.rscinema.finalproject.domain.dto.showtime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterShowTimeDTO {
    private Integer id;
    @NotNull(message = "Movie must not be null!")
    private Integer movie;
    @NotNull(message = "Room must not be null!")
    private Integer room;
    @NotNull(message = "Date must not be null!")
    private LocalDate date;
    @NotNull(message = "Start Time must not be null!")
    private LocalTime startTime;
    @NotNull(message = "Price must not be null!")
    private Double price;
}
