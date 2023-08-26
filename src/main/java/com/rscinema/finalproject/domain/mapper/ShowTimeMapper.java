package com.rscinema.finalproject.domain.mapper;

import com.rscinema.finalproject.domain.dto.showtime.ShowTimeCustomerDTO;
import com.rscinema.finalproject.domain.dto.showtime.ShowTimeDTO;
import com.rscinema.finalproject.domain.dto.showtime.UpdateShowTimeDTO;
import com.rscinema.finalproject.domain.entity.ShowTime;

public class ShowTimeMapper {

    public static ShowTimeDTO toDTOShowTime(ShowTime showTime) {

        return ShowTimeDTO.builder()
                .id(showTime.getId())
                .movie(showTime.getMovie().getTitle())
                .room(showTime.getRoom().getName())
                .startDate(showTime.getStartDate())
                .startTime(showTime.getStartTime())
                .endDate(showTime.getEndDate())
                .endTime(showTime.getEndTime())
                .readyForNextTime(showTime.getReadyForNextTime().toLocalTime())
                .price(showTime.getPrice())
                .finished(showTime.getFinished())
                .build();
    }

    public static ShowTimeCustomerDTO toDTOShowTimeCustomer(ShowTime showTime) {
        return ShowTimeCustomerDTO.builder()
                .id(showTime.getId())
                .movie(showTime.getMovie().getTitle())
                .room(showTime.getRoom().getName())
                .startDate(showTime.getStartDate())
                .startTime(showTime.getStartTime())
                .endTime(showTime.getEndTime())
                .build();
    }

    public static ShowTime toEntity(ShowTimeDTO dto) {

        return ShowTime.builder()
                .startDate(dto.getStartDate())
                .startTime(dto.getStartTime())
                .price(dto.getPrice())
                .finished(false)
                .build();
    }

    public static ShowTime update(ShowTime entity, UpdateShowTimeDTO dto) {
        entity.setStartDate(dto.getStartDate());
        entity.setStartTime(dto.getStartTime());
        return entity;
    }
}
