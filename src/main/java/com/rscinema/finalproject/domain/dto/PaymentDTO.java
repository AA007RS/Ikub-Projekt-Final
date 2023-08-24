package com.rscinema.finalproject.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Builder
@Getter
@Setter
public class PaymentDTO {
    private Integer id;
    private String paymentMethod;
    private Double amount;
    private Integer orderId;
}
