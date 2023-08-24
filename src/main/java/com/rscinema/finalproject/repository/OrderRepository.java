package com.rscinema.finalproject.repository;

import com.rscinema.finalproject.domain.entity.order.Order;
import com.rscinema.finalproject.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Integer> {

    Optional<Order> findByUserAndClosedIsFalse(User user);

    @Query("SELECT o from Order o WHERE "+
    "(o.createdAt > :from OR :from is NULL) AND " +
    "(o.createdAt < :to OR :to Is Null)")
    List<Order> findFromToDate(@Param("from") LocalDateTime from, @Param("to") LocalDateTime to);
}
