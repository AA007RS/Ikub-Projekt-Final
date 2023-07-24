package com.rscinema.finalproject.domain.entity.order;

import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum OrderStatus {

    DECLINED("DECLINED"),
    CONFIRMED("CONFIRMED"),
    IN_PROGRESS("IN_PROGRESS");

    private final String value;

    public static OrderStatus fromValue(String orderStatus){
        return Arrays.stream(OrderStatus.values()).filter(r->r.value.equalsIgnoreCase(orderStatus))
                .findFirst()
                .orElseThrow(()->new ResourceNotFoundException(String
                        .format("Order Status %s not found",orderStatus)));
    }

    public String getValue(){return value;}
}
