package com.rscinema.finalproject.service;

import com.rscinema.finalproject.domain.dto.PaymentDTO;

public interface PaymentService {
    PaymentDTO findById(Integer id);
}
