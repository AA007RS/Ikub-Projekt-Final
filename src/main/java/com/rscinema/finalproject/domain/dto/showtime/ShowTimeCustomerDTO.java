package com.rscinema.finalproject.domain.dto.showtime;

import java.time.LocalDate;
import java.time.LocalTime;

public class ShowTimeCustomerDTO {
    private Integer id;
    private String movie;
    private String room;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalTime endTime;

}
