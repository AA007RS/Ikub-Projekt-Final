package com.rscinema.finalproject.controller;

import com.rscinema.finalproject.domain.dto.ticket.TicketDTO;
import com.rscinema.finalproject.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/admin/filter")
    public ResponseEntity<List<TicketDTO>> filterTicketsPerShowTimeAdmin(@RequestParam Integer showTimeId,
                                                                         @RequestParam(required = false) boolean reserved,
                                                                         @RequestParam(required = false) Integer row) {
        return ResponseEntity.ok(ticketService.watchAndFilterTicketsOfShowTimeAdmin(showTimeId, reserved, row));
    }
}
