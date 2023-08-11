package com.rscinema.finalproject.service;

import com.rscinema.finalproject.domain.dto.showtime.RegisterShowTimeDTO;
import com.rscinema.finalproject.domain.dto.showtime.ShowTimeDTO;
import com.rscinema.finalproject.domain.entity.ShowTime;

import java.util.List;

public interface ShowTimeService {

    ShowTimeDTO create(RegisterShowTimeDTO dto);
    List<ShowTime> findByRoomAndDate(String room, String date);
    List<ShowTimeDTO> findByDate(String date);

}
