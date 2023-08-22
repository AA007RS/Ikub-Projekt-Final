package com.rscinema.finalproject.service;

import com.rscinema.finalproject.domain.dto.OrderDTO;

public interface OrderService {

    OrderDTO findById(Integer id);
    OrderDTO addTicket(Integer ticketId);
    OrderDTO removeTicket(Integer ticketId);
    void delete(Integer id);
}
