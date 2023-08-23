package com.rscinema.finalproject.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDTO {
    private String paymentMethod;
    private Double amount;

}
