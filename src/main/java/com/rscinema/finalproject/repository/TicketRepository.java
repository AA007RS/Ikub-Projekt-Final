package com.rscinema.finalproject.repository;

import com.rscinema.finalproject.domain.entity.ShowTime;
import com.rscinema.finalproject.domain.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    @Modifying
    @Query("UPDATE Ticket t SET t.deleted=:deleted where t.showTime.id=:sht")
    void updateDeleted(@Param("deleted") boolean deleted, @Param("sht") Integer sht);

    @Modifying
    @Query("UPDATE Ticket t SET t.deleted = true where t.showTime.id = :showTime")
    void updateFromExpiredShowtime(@Param("showTime") Integer showTime);
    @Query("SELECT t FROM Ticket t where t.showTime.id = :sht AND " +
            "(t.reserved = :reserved OR :reserved IS NULL) AND " +
            "(t.rowNumber = :row OR :row IS NULL) " +
            "ORDER BY t.rowNumber ASC, t.seatNumber ASC")
    List<Ticket> watchTicketsAdmin(@Param("sht") Integer showTime, @Param("reserved") boolean reserved,
                                   @Param("row") Integer row);

    List<Ticket> findAllByShowTimeAndDeletedFalseAndShowTime_FinishedIsFalse(ShowTime showTime);

    Optional<Ticket> findByIdAndReservedIsFalse(Integer id);

    @Query("SELECT t from Ticket t WHERE " +
            "t.deleted = false AND t.reserved is true AND " +
            "t.showTime.startDate < :startDate AND " +
            "t.showTime.startTime < :startTime")
    List<Ticket> findAllExpiredReservedTickets(@Param("startDate") LocalDate startDate,
                                               @Param("startTime") LocalTime startTime);
}
