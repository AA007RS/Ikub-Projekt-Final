package com.rscinema.finalproject.domain.mapper;

import com.rscinema.finalproject.domain.dto.OrderDTO;
import com.rscinema.finalproject.domain.dto.ticket.TicketDTO;
import com.rscinema.finalproject.domain.entity.order.Order;

import java.util.List;

public class OrderMapper {

    public static OrderDTO toDTO(Order order){
        List<TicketDTO> ticketDTOList = order.getTickets().stream().map(TicketMapper::toDTO).toList();
        return OrderDTO.builder()
                .id(order.getId())
                .closed(order.getClosed())
                .ticketDTOList(ticketDTOList)
                .userId(order.getUser().getId())
                .totalPrice(order.getTotalPrice().toString().concat(" $"))
                .payment(order.getPayment().getId())
                .build();
    }
}
