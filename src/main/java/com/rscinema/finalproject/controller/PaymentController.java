package com.rscinema.finalproject.controller;

import com.rscinema.finalproject.domain.dto.PaymentDTO;
import com.rscinema.finalproject.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> findById(@PathVariable("id")Integer id){
        return ResponseEntity.ok(paymentService.findById(id));
    }
}
