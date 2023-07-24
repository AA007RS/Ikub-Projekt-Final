package com.rscinema.finalproject.domain.entity.payment;

import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum PaymentMethod {

    CARD("CARD",0.5),
    PAYPAL("PAYPAL",1.0),
    CRYPTOCURRENCY("CRYPTOCURRENCY",0.0);

    private final String value;
    private final Double serviceFee;
    public static PaymentMethod fromValue(String payment){
        return Arrays.stream(PaymentMethod.values()).filter(r->r.value.equalsIgnoreCase(payment))
                .findFirst()
                .orElseThrow(()->new ResourceNotFoundException(String
                        .format("Payment method %s not found!",payment)));
    }
    public String getValue(){return value;}
    public Double getServiceFee(){return serviceFee;}
}
