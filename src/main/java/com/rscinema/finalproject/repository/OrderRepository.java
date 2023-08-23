package com.rscinema.finalproject.repository;

import com.rscinema.finalproject.domain.entity.order.Order;
import com.rscinema.finalproject.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    Optional<Order> findByUserAndClosedIsFalse(User user);
}