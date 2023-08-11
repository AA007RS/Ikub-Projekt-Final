package com.rscinema.finalproject.domain.mapper;

import com.rscinema.finalproject.domain.dto.showtime.ShowTimeDTO;
import com.rscinema.finalproject.domain.entity.ShowTime;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ShowTimeMapper {

    public static ShowTimeDTO toDTO(ShowTime showTime){
        return ShowTimeDTO.builder()
                .id(showTime.getId())
                .movie(showTime.getMovie().getTitle())
                .room(showTime.getRoom().getName())
                .date(showTime.getDate().toString())
                .startTime(showTime.getStartTime().toString())
                .endTime(showTime.getEndTime().toString())
                .readyForNextTime(showTime.getReadyForNextTime().toString())
                .price(showTime.getPrice())
                .build();
    }

    public static ShowTime toEntity(ShowTimeDTO dto) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(dto.getDate());
        LocalTime time = LocalTime.parse(dto.getStartTime());

        return ShowTime.builder()
                .date(date)
                .startTime(time)
                .price(dto.getPrice())
                .build();
    }
}
