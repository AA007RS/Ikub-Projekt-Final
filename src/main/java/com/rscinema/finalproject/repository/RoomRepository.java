package com.rscinema.finalproject.repository;

import com.rscinema.finalproject.domain.dto.RoomDTO;
import com.rscinema.finalproject.domain.entity.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room,Integer> {

    Optional<Room> findByNameIgnoreCase(String name);
    List<Room> findAllByDeletedFalse();

}
