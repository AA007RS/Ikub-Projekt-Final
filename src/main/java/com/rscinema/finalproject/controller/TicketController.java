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

    @PutMapping("/admin/{id}")
    public ResponseEntity<String> disableTicket(@PathVariable("id")Integer id){
        ticketService.disableTicket(id);
        return ResponseEntity.ok(String.format("Ticket with id %s disabled!",id));
    }

    @GetMapping("/customer/byShowtime/{id}/all")
    public ResponseEntity <List<TicketDTO>> filterTicketsPerShowTimeCustomer(@PathVariable("id") Integer showTimeId){
        return ResponseEntity.ok(ticketService.retrieveAllByShowtime(showTimeId));
    }

    @GetMapping("/customer/view-my-tickets")
    public ResponseEntity<List<TicketDTO>> viewMyActiveTickets(){
        return ResponseEntity.ok(ticketService.viewMyTickets());
    }
}
