package com.rscinema.finalproject.domain.mapper;

import com.rscinema.finalproject.domain.dto.showtime.ShowTimeDTO;
import com.rscinema.finalproject.domain.entity.ShowTime;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ShowTimeMapper {

    public static ShowTimeDTO toDTO(ShowTime showTime){

        return ShowTimeDTO.builder()
                .id(showTime.getId())
                .movie(showTime.getMovie().getTitle())
                .room(showTime.getRoom().getName())
                .date(showTime.getDate())
                .startTime(showTime.getStartTime())
                .endTime(showTime.getEndTime().toLocalTime())
                .readyForNextTime(showTime.getReadyForNextTime().toLocalTime())
                .price(showTime.getPrice())
                .build();
    }

    public static ShowTime toEntity(ShowTimeDTO dto) throws DateTimeParseException {

        return ShowTime.builder()
                .date(dto.getDate())
                .startTime(dto.getStartTime())
                .price(dto.getPrice())
                .build();
    }
}
