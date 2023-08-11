package com.rscinema.finalproject.controller;

import com.rscinema.finalproject.domain.dto.showtime.ShowTimeDTO;
import com.rscinema.finalproject.domain.entity.ShowTime;
import com.rscinema.finalproject.service.ShowTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/showtimes")
@RequiredArgsConstructor
public class ShowTimeController {

    private final ShowTimeService showTimeService;

    @PostMapping()
    public ResponseEntity<ShowTimeDTO> create(@RequestBody ShowTimeDTO showTimeDTO){
        return ResponseEntity.ok(showTimeService.create(showTimeDTO));
    }

    @GetMapping("/getByRoomAndDate")
    public ResponseEntity<List<ShowTime>> findByRoomAndDate(){
        return ResponseEntity.ok(showTimeService.findByRoomAndDate("first room","07-12-2000"));
    }
}
