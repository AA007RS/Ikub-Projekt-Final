package com.rscinema.finalproject.repository;

import com.rscinema.finalproject.domain.entity.ShowTime;
import com.rscinema.finalproject.domain.entity.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ShowTimeRepository extends JpaRepository<ShowTime,Integer> {

    List<ShowTime> findByRoomAndDateOrderByDateAscStartTimeAsc(Room room, LocalDate date);
    List<ShowTime> findByDateOrderByStartTime(LocalDate date);

}
