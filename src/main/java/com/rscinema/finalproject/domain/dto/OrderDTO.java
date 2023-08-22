package com.rscinema.finalproject.domain.dto;

import com.rscinema.finalproject.domain.dto.ticket.TicketDTO;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Integer id;
    private Integer userId;
    private List<TicketDTO> ticketDTOList;
    private String totalPrice;
    private Integer payment;
    private Boolean closed;
}
