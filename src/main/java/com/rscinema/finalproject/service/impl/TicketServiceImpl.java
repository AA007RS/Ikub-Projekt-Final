package com.rscinema.finalproject.service.impl;

import com.rscinema.finalproject.domain.dto.ticket.TicketDTO;
import com.rscinema.finalproject.domain.entity.ShowTime;
import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import com.rscinema.finalproject.domain.mapper.TicketMapper;
import com.rscinema.finalproject.repository.ShowTimeRepository;
import com.rscinema.finalproject.repository.TicketRepository;
import com.rscinema.finalproject.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
