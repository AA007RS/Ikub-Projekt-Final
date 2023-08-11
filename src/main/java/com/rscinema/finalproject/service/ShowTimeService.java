package com.rscinema.finalproject.service;

import com.rscinema.finalproject.domain.dto.showtime.ShowTimeDTO;
import com.rscinema.finalproject.domain.entity.ShowTime;

import java.util.List;

public interface ShowTimeService {

    ShowTimeDTO create(ShowTimeDTO dto);
    List<ShowTime> findByRoomAndDate(String room, String date);

}
