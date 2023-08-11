package com.rscinema.finalproject.service.impl;

import com.rscinema.finalproject.domain.dto.RoomDTO;
import com.rscinema.finalproject.domain.entity.room.Room;
import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import com.rscinema.finalproject.domain.mapper.RoomMapper;
import com.rscinema.finalproject.repository.RoomRepository;
import com.rscinema.finalproject.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    @Override
    public RoomDTO create(RoomDTO dto) {
        Room toSave = RoomMapper.toEntity(dto);
        return RoomMapper.toDTO(roomRepository.save(toSave));
    }

    @Override
    public Room findById(Integer id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(
                        "Room with id %s is not found!",id
                )));
    }

    @Override
    public List<RoomDTO> findNonDeletedRooms() {
        return roomRepository.findAllByDeletedFalse().stream()
                .map(RoomMapper::toDTO)
                .toList();
    }

    @Override
    public RoomDTO update(RoomDTO dto) {
        Room toUpdate = RoomMapper.toUpdate(findById(dto.getId()),dto);
        return RoomMapper.toDTO(roomRepository.save(toUpdate));
    }

    @Override
    public void softDelete(Integer id) {
        Room room = findById(id);
        room.setDeleted(true);
        roomRepository.save(room);
    }

    @Override
    public void restore(Integer id) {
        Room room = findById(id);
        room.setDeleted(false);
        roomRepository.save(room);
    }
}
