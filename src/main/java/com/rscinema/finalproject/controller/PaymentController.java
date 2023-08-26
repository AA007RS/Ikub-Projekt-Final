package com.rscinema.finalproject.controller;

import com.rscinema.finalproject.domain.dto.PaymentDTO;
import com.rscinema.finalproject.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/byId/{id}")
    public ResponseEntity<PaymentDTO> findById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @GetMapping("/admin/search")
    public ResponseEntity<List<PaymentDTO>> search(@RequestParam(required = false) LocalDate from,
                                                   @RequestParam(required = false) LocalDate to) {
        return ResponseEntity.ok(paymentService.viewCompletedPaymentsFromTo(from, to));
    }

}
