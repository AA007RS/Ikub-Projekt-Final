package com.rscinema.finalproject.domain.mapper;

import com.rscinema.finalproject.domain.dto.RoomDTO;
import com.rscinema.finalproject.domain.entity.room.Room;
import com.rscinema.finalproject.domain.entity.room.RoomSizes;

public class RoomMapper {

    public static Room toEntity(RoomDTO dto){
        return Room.builder()
                .name(dto.getName())
                .roomSize(RoomSizes.fromValue(dto.getRoomSize()))
                .build();
    }

    public static RoomDTO toDTO(Room room){
        return RoomDTO.builder()
                .id(room.getId())
                .name(room.getName())
                .roomSize(room.getRoomSize().getType())
                .rowNum(room.getRoomSize().getRow_num())
                .rowSize(room.getRoomSize().getRow_size())
                .build();
    }

    public static Room toUpdate(Room e, RoomDTO dto){
        e.setName(dto.getName());
        e.setRoomSize(RoomSizes.fromValue(dto.getRoomSize()));
        return e;
    }
}
