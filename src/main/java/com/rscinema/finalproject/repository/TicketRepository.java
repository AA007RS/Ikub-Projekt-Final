package com.rscinema.finalproject.repository;

import com.rscinema.finalproject.domain.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
