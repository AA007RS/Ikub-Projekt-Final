package com.rscinema.finalproject.service;

import com.rscinema.finalproject.domain.dto.OrderDTO;
import com.rscinema.finalproject.domain.dto.PaymentDTO;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {

    OrderDTO findById(Integer id);

    OrderDTO addTicket(Integer ticketId);

    OrderDTO removeTicket(Integer ticketId);

    void delete(Integer id);

    OrderDTO pay(Integer orderId, PaymentDTO dto);

    OrderDTO viewOrderByTicketId(Integer ticketId);

    List<OrderDTO> viewCompletedOrdersFromTo(LocalDate from, LocalDate to);
}
