package com.rscinema.finalproject.repository;

import com.rscinema.finalproject.domain.entity.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Integer> {

    @Query("SELECT r FROM Room r WHERE " +
            "(r.name ILIKE concat('%',:name,'%') OR :name IS NULL) AND " +
            "(r.deleted = :deleted OR :deleted IS NULL) ORDER BY r.createdAt ASC")
    List<Room> searchRooms(@Param("name") String roomName, @Param("deleted") Boolean deleted);

}
