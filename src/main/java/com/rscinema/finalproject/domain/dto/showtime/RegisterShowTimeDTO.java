package com.rscinema.finalproject.domain.dto.showtime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

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
    @NotBlank(message = "Date must not be blank!")
    private String date;
    @NotNull(message = "Start Time must not be null!")
    @NotBlank(message = "Start Time must not be blank!")
    private String startTime;
    @NotNull(message = "Price must not be null!")
    private Double price;
}
