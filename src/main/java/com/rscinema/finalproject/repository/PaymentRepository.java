package com.rscinema.finalproject.repository;

import com.rscinema.finalproject.domain.entity.order.Order;
import com.rscinema.finalproject.domain.entity.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query("SELECT p from Payment p WHERE " +
            "(p.createdAt > :from OR :from is NULL) AND " +
            "(p.createdAt < :to OR :to Is Null) AND" +
            " p.order.closed = true")
    List<Payment> findFromToDate(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

}
