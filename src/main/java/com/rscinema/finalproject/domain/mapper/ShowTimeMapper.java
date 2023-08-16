package com.rscinema.finalproject.domain.mapper;

import com.rscinema.finalproject.domain.dto.showtime.ShowTimeDTO;
import com.rscinema.finalproject.domain.entity.ShowTime;

import java.time.format.DateTimeParseException;

public class ShowTimeMapper {

    public static ShowTimeDTO toDTO(ShowTime showTime){

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
                .build();
    }

    public static ShowTime toEntity(ShowTimeDTO dto) throws DateTimeParseException {

        return ShowTime.builder()
                .startDate(dto.getStartDate())
                .startTime(dto.getStartTime())
                .price(dto.getPrice())
                .build();
    }
}
