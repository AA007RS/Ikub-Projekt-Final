package com.rscinema.finalproject.controller;

import com.rscinema.finalproject.domain.dto.showtime.RegisterShowTimeDTO;
import com.rscinema.finalproject.domain.dto.showtime.ShowTimeDTO;
import com.rscinema.finalproject.service.ShowTimeService;
import jakarta.validation.Valid;
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
    public ResponseEntity<ShowTimeDTO> create(@Valid @RequestBody RegisterShowTimeDTO dto){
        return ResponseEntity.ok(showTimeService.create(dto));
    }

    @GetMapping("/getByDate/{date}")
    public ResponseEntity<List<ShowTimeDTO>> findByDate(@PathVariable("date") String date){
        return ResponseEntity.ok(showTimeService.findByDate(date));
    }
}
