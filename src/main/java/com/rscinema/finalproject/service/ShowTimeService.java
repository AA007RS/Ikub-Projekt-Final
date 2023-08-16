package com.rscinema.finalproject.service;

import com.rscinema.finalproject.domain.dto.showtime.RegisterShowTimeDTO;
import com.rscinema.finalproject.domain.dto.showtime.ShowTimeDTO;
import com.rscinema.finalproject.domain.dto.showtime.ShowTimeSearchDTO;
import com.rscinema.finalproject.domain.entity.ShowTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ShowTimeService {

    ShowTimeDTO create(RegisterShowTimeDTO dto);
    List<ShowTime> findByRoomAndDate(Integer room, LocalDate date);
    ShowTime findById(Integer id);
    List<ShowTimeDTO> search(ShowTimeSearchDTO dto);
}
