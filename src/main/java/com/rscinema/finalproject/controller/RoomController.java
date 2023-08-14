package com.rscinema.finalproject.controller;


import com.rscinema.finalproject.domain.dto.room.RoomDTO;
import com.rscinema.finalproject.domain.dto.room.RoomSearchDTO;
import com.rscinema.finalproject.domain.entity.room.Room;
import com.rscinema.finalproject.domain.mapper.RoomMapper;
import com.rscinema.finalproject.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rooms")
public class RoomController {
    private final RoomService roomService;

    @PostMapping()
    public ResponseEntity<RoomDTO> create(@RequestBody RoomDTO roomDTO){
        return ResponseEntity.ok(roomService.create(roomDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> findById(@PathVariable("id")Integer id){
        Room room = roomService.findById(id);
        return ResponseEntity.ok(RoomMapper.toDTO(room));
    }

    @GetMapping()
    public ResponseEntity<List<RoomDTO>> findAllNonDeletedRooms(){
        return ResponseEntity.ok(roomService.findNonDeletedRooms());
    }

    @GetMapping("/admin/search")
    public ResponseEntity<List<RoomDTO>> search(@RequestBody RoomSearchDTO dto){
        return ResponseEntity.ok(roomService.searchRoom(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> update(@PathVariable("id")Integer id,
                                          @RequestBody RoomDTO dto){
        dto.setId(id);
        return ResponseEntity.ok(roomService.update(dto));
    }

    @PutMapping("/delete/{id}")
    public ResponseEntity<String> softDelete(@PathVariable("id")Integer id){
        roomService.softDelete(id);
        return new ResponseEntity<>(String.format("Room with id %s is softly deleted!", id),
                HttpStatus.OK);
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<String> restore(@PathVariable("id")Integer id){
        roomService.restore(id);
        return new ResponseEntity<>(String.format("Room with id %s is restored!", id),
                HttpStatus.OK);
    }

}
