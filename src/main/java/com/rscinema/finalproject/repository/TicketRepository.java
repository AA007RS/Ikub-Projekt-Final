package com.rscinema.finalproject.repository;

import com.rscinema.finalproject.domain.entity.ShowTime;
import com.rscinema.finalproject.domain.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
    @Modifying
    @Query("UPDATE Ticket t SET t.deleted=:deleted where t.showTime.id=:sht")
    void updateDeleted(@Param("deleted")boolean deleted,@Param("sht") Integer sht);

    @Modifying
    @Query("UPDATE Ticket t SET t.deleted = true where t.showTime = :showTime")
    void updateFromExpiredShowtime(@Param("showTime")ShowTime showTime);


}
