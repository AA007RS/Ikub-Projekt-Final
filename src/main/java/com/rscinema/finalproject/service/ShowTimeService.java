package com.rscinema.finalproject.service;

import com.rscinema.finalproject.domain.dto.showtime.*;
import com.rscinema.finalproject.domain.entity.ShowTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ShowTimeService {

    ShowTimeDTO create(RegisterShowTimeDTO dto);
    List<ShowTime> findByRoomAndDate(Integer room, LocalDate date);
    ShowTime findById(Integer id);
    List<ShowTimeDTO> search(ShowTimeSearchDTO dto);
    ShowTimeDTO update(UpdateShowTimeDTO dto);
    String delete(Integer id);
    void restore(Integer id);
    ShowTimeCustomerDTO findByIdCustomer(Integer id);
    List<ShowTimeCustomerDTO> searchCustomerView(String movie, LocalDate date);
}
