package com.rscinema.finalproject.repository;

import com.rscinema.finalproject.domain.entity.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {

}
