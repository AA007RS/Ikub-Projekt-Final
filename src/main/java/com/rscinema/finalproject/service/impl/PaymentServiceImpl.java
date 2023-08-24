package com.rscinema.finalproject.service.impl;

import com.rscinema.finalproject.domain.dto.PaymentDTO;
import com.rscinema.finalproject.domain.entity.payment.Payment;
import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import com.rscinema.finalproject.domain.mapper.PaymentMapper;
import com.rscinema.finalproject.repository.PaymentRepository;
import com.rscinema.finalproject.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    @Override
    public PaymentDTO findById(Integer id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(
                        "Payment with id %s not found!",id
                ))
        );
        return PaymentMapper.toDTO(payment);
    }
}
