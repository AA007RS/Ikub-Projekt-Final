package com.rscinema.finalproject.service;

import com.rscinema.finalproject.domain.dto.room.RoomDTO;
import com.rscinema.finalproject.domain.dto.room.RoomSearchDTO;
import com.rscinema.finalproject.domain.entity.room.Room;

import java.util.List;

public interface RoomService {
    RoomDTO create(RoomDTO dto);

    Room findById(Integer id);

    List<RoomDTO> searchRoom(RoomSearchDTO dto);

    RoomDTO update(RoomDTO dto);

    void softDelete(Integer id);

    void restore(Integer id);
}
