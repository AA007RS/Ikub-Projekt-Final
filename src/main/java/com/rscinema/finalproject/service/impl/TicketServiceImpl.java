package com.rscinema.finalproject.service.impl;

import com.rscinema.finalproject.configuration.SecurityUtils;
import com.rscinema.finalproject.domain.dto.ticket.TicketDTO;
import com.rscinema.finalproject.domain.entity.ShowTime;
import com.rscinema.finalproject.domain.entity.Ticket;
import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import com.rscinema.finalproject.domain.mapper.TicketMapper;
import com.rscinema.finalproject.repository.ShowTimeRepository;
import com.rscinema.finalproject.repository.TicketRepository;
import com.rscinema.finalproject.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Security;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final ShowTimeRepository showTimeRepository;

    @Override
    public List<TicketDTO> watchAndFilterTicketsOfShowTimeAdmin(Integer showTimeId, boolean reserved, Integer row) {
        ShowTime showTime = showTimeRepository.findById(showTimeId)
                .orElseThrow(()-> new ResourceNotFoundException(String.format(
                       " Showtime with id %s not exist",showTimeId
                )));
        return ticketRepository.watchTicketsAdmin(showTime.getId(),reserved,row).stream()
                .map(TicketMapper::toDTO)
                .toList();
    }

    @Override
    public Ticket findById(Integer id) {
        return ticketRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException(String.format(
                        "Ticket with id %s not found!",id
                )));
    }

    @Override
    public void disableTicket(Integer id) {
        Ticket ticket = findById(id);
        ticket.setDeleted(true);
        ticketRepository.save(ticket);
    }

    @Override
    public List<TicketDTO> retrieveAllByShowtime(Integer showTimeId) {
        ShowTime showTime = showTimeRepository.findById(showTimeId)
                .orElseThrow(()->new ResourceNotFoundException(String.format(
                        "Showtime with id %s not found!",showTimeId
                )));

        return ticketRepository.findAllByShowTimeAndDeletedFalseAndShowTime_FinishedIsFalse(showTime).stream()
                .map(TicketMapper::toDTO)
                .toList();
    }

    @Override
    public List<TicketDTO> viewMyTickets() {
        return ticketRepository.findAllByOrder_User_IdAndShowTime_FinishedIsFalse(SecurityUtils.getLoggedUserId())
                .stream()
                .map(TicketMapper::toDTO)
                .toList();
    }
}
