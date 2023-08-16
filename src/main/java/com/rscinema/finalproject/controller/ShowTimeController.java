package com.rscinema.finalproject.controller;

import com.rscinema.finalproject.domain.dto.showtime.RegisterShowTimeDTO;
import com.rscinema.finalproject.domain.dto.showtime.ShowTimeDTO;
import com.rscinema.finalproject.domain.dto.showtime.ShowTimeSearchDTO;
import com.rscinema.finalproject.domain.dto.showtime.UpdateShowTimeDTO;
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
    public ResponseEntity<ShowTimeDTO> create(@Valid @RequestBody RegisterShowTimeDTO dto) {
        return ResponseEntity.ok(showTimeService.create(dto));
    }

    @GetMapping("/admin/search")
    public ResponseEntity<List<ShowTimeDTO>> search(@RequestBody ShowTimeSearchDTO dto) {
        return ResponseEntity.ok(showTimeService.search(dto));
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<ShowTimeDTO> update(@PathVariable("id") Integer id,
                                              @Valid @RequestBody UpdateShowTimeDTO dto) {
        dto.setCurrentId(id);
        return ResponseEntity.ok(showTimeService.update(dto));
    }
}
