package com.rscinema.finalproject.domain.dto.showtime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Setter
public class ShowTimeCustomerDTO {
    private Integer id;
    private String movie;
    private String room;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalTime endTime;

}
