package com.rscinema.finalproject.service;

import com.rscinema.finalproject.domain.dto.ticket.TicketDTO;
import com.rscinema.finalproject.domain.entity.Ticket;

import java.util.List;

public interface TicketService {

    List<TicketDTO> watchAndFilterTicketsOfShowTimeAdmin(Integer showTimeId, boolean reserved, Integer row);

    Ticket findById(Integer id);

    void disableTicket(Integer id);

    List<TicketDTO> retrieveAllByShowtime(Integer showTimeId);

    List<TicketDTO> viewMyTickets();

}
