package com.rscinema.finalproject.domain.dto.ticket;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Builder
@Getter
@Setter
public class TicketDTO {
    private Integer id;
    private String movie;
    private String room;
    private LocalDate date;
    private LocalTime time;
    private String duration;
    private Integer row;
    private Integer seat;
    private String price;
}
