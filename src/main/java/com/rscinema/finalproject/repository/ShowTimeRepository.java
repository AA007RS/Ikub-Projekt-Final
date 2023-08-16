package com.rscinema.finalproject.repository;

import com.rscinema.finalproject.domain.entity.Movie;
import com.rscinema.finalproject.domain.entity.ShowTime;
import com.rscinema.finalproject.domain.entity.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

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

    List<ShowTime> findByRoomAndStartDateOrderByStartDateAscStartTimeAsc(Room room, LocalDate date);
}
