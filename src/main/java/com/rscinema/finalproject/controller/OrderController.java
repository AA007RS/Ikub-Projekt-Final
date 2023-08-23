package com.rscinema.finalproject.controller;

import com.rscinema.finalproject.domain.dto.OrderDTO;
import com.rscinema.finalproject.domain.dto.PaymentDTO;
import com.rscinema.finalproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/customer/addTicket/{id}")
    public ResponseEntity<OrderDTO> addTicket(@PathVariable("id") Integer id){
        return ResponseEntity.ok(orderService.addTicket(id));
    }

    @PutMapping("/customer/removeTicket/{id}")
    public ResponseEntity<OrderDTO> removeTicket(@PathVariable("id")Integer id){
        return ResponseEntity.ok(orderService.removeTicket(id));
    }

    @DeleteMapping("/customer/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id")Integer id){
        orderService.delete(id);
        return ResponseEntity.ok("Ok");
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<OrderDTO> getById(@PathVariable("id")Integer id){
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PutMapping("/customer/{id}/pay")
    public ResponseEntity<OrderDTO> pay(@PathVariable("id")Integer orderId,
                                        @RequestBody PaymentDTO dto){
        return ResponseEntity.ok(orderService.pay(orderId,dto));
    }
}
