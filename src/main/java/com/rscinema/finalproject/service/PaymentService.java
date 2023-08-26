package com.rscinema.finalproject.service;

import com.rscinema.finalproject.domain.dto.OrderDTO;
import com.rscinema.finalproject.domain.dto.PaymentDTO;

import java.time.LocalDate;
import java.util.List;

public interface PaymentService {
    PaymentDTO findById(Integer id);

    List<PaymentDTO> viewCompletedPaymentsFromTo(LocalDate from, LocalDate to);
}
