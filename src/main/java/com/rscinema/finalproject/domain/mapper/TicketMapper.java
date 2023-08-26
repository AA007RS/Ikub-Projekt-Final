package com.rscinema.finalproject.domain.mapper;

import com.rscinema.finalproject.domain.dto.ticket.TicketDTO;
import com.rscinema.finalproject.domain.entity.Ticket;

public class TicketMapper {

    public static TicketDTO toDTO(Ticket ticket) {
        return TicketDTO.builder()
                .id(ticket.getId())
                .movie(ticket.getShowTime().getMovie().getTitle())
                .room(ticket.getShowTime().getRoom().getName())
                .row(ticket.getRowNumber())
                .seat(ticket.getSeatNumber())
                .date(ticket.getShowTime().getStartDate())
                .time(ticket.getShowTime().getStartTime())
                .duration(ticket.getShowTime().getMovie().getLength().toString().concat(" m"))
                .price(ticket.getShowTime().getPrice().toString().concat(" $"))
                .build();
    }
}
