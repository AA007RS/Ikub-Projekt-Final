package com.rscinema.finalproject.repository;

import com.rscinema.finalproject.domain.dto.showtime.ShowTimeCustomerDTO;
import com.rscinema.finalproject.domain.entity.ShowTime;
import com.rscinema.finalproject.domain.entity.room.Room;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface ShowTimeRepository extends JpaRepository<ShowTime, Integer> {

    @Query("SELECT sht FROM ShowTime sht " +
            "Inner Join sht.movie m " +
            "Inner Join sht.room r " +
            "Where " +
            "(m.id = :movie OR :movie IS NULL) AND " +
            "(r.id = :room OR :room IS NULL) AND " +
            "(:startDate IS NULL OR sht.startDate = :startDate) AND " +
            "(:startTime IS NULL OR sht.startTime = :startTime) AND " +
            "(:endDate IS NULL OR sht.endDate = :endDate) AND " +
            "(:endTime IS NULL OR sht.endTime = :endTime) AND " +
            "(:deleted IS NULL OR sht.deleted = :deleted) ")
    List<ShowTime> searchShowTimes(@Param("movie") Integer movie, @Param("room") Integer room,
                                   @Param("startDate") LocalDate startDate, @Param("startTime") LocalTime startTime,
                                   @Param("endDate") LocalDate endDate, @Param("endTime") LocalTime endTime,
                                   @Param("deleted") Boolean deleted);

    List<ShowTime> findByRoomAndStartDateAndDeletedIsFalseOrderByStartDateAscStartTimeAsc(Room room, LocalDate date);

    Optional<ShowTime> findByIdAndDeletedIsFalse(Integer id);
    @Modifying
    @Query("UPDATE ShowTime sh SET sh.deleted=true WHERE " +
            "sh.endDate <= :date AND sh.endTime <= :time")
    void updateAll(@Param("date") LocalDate date,@Param("time") LocalTime time);


    @Query("SELECT sh FROM ShowTime sh WHERE " +
            "(sh.movie.title ILIKE concat(:movieTitle,'%') OR :movieTitle IS NULL) AND" +
            "(sh.startDate = :date OR :date IS NULL) AND " +
            "sh.deleted = false " +
            "ORDER BY sh.startDate ASC," +
            "sh.movie.title ASC")
    List<ShowTime> searchCustomerView(@Param("movieTitle") String movieTitle, @Param("date") LocalDate date);
}
