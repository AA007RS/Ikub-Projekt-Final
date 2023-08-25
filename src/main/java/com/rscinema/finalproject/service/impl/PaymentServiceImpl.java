package com.rscinema.finalproject.service.impl;

import com.rscinema.finalproject.domain.dto.PaymentDTO;
import com.rscinema.finalproject.domain.entity.payment.Payment;
import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import com.rscinema.finalproject.domain.mapper.PaymentMapper;
import com.rscinema.finalproject.repository.PaymentRepository;
import com.rscinema.finalproject.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CUSTOMER')")
    @Override
    public PaymentDTO findById(Integer id) {
        Payment payment = paymentRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format(
                        "Payment with id %s not found!",id
                ))
        );
        return PaymentMapper.toDTO(payment);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public List<PaymentDTO> viewCompletedPaymentsFromTo(LocalDate from, LocalDate to) {
        LocalDateTime from2 = null;
        LocalDateTime to2 = null;
        if (from!=null){
            from2 = LocalDateTime.of(from,LocalTime.parse("00:00"));

        } else if (to!=null) {
            to2 = LocalDateTime.of(to,LocalTime.parse("23:59"));
        }
        return paymentRepository.findFromToDate(from2, to2)
                .stream()
                .map(PaymentMapper::toDTO)
                .toList();
    }
}
