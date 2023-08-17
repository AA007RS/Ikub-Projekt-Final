package com.rscinema.finalproject.service;

import com.rscinema.finalproject.domain.dto.ticket.TicketDTO;

import java.util.List;

public interface TicketService {

    List<TicketDTO> watchAndFilterTicketsOfShowTimeAdmin(Integer showTimeId, boolean reserved, Integer row);

}
