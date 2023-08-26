package com.rscinema.finalproject.domain.mapper;

import com.rscinema.finalproject.domain.dto.PaymentDTO;
import com.rscinema.finalproject.domain.entity.payment.Payment;
import com.rscinema.finalproject.domain.entity.payment.PaymentMethod;

public class PaymentMapper {

    public static Payment toUpdate(Payment e, PaymentDTO d) {
        PaymentMethod paymentMethod = PaymentMethod.fromValue(d.getPaymentMethod());
        e.setPaymentMethod(paymentMethod);
        e.setAmount(d.getAmount());
        return e;
    }

    public static PaymentDTO toDTO(Payment entity) {
        return PaymentDTO.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .paymentMethod(entity.getPaymentMethod().getValue())
                .orderId(entity.getOrder().getId())
                .build();
    }
}
