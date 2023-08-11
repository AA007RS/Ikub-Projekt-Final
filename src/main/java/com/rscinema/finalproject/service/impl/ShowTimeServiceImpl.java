package com.rscinema.finalproject.service.impl;

import com.rscinema.finalproject.domain.dto.showtime.ShowTimeDTO;
import com.rscinema.finalproject.domain.entity.Movie;
import com.rscinema.finalproject.domain.entity.ShowTime;
import com.rscinema.finalproject.domain.entity.room.Room;
import com.rscinema.finalproject.domain.exception.DateException;
import com.rscinema.finalproject.domain.exception.HourConfusion;
import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import com.rscinema.finalproject.domain.mapper.ShowTimeMapper;
import com.rscinema.finalproject.repository.MovieRepository;
import com.rscinema.finalproject.repository.RoomRepository;
import com.rscinema.finalproject.repository.ShowTimeRepository;
import com.rscinema.finalproject.service.ShowTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowTimeServiceImpl implements ShowTimeService {

    private final ShowTimeRepository showTimeRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;

    @Override
    public ShowTimeDTO create(ShowTimeDTO dto) {
        //find existing movie
        Movie movie = movieRepository.findByTitleIgnoreCase(dto.getMovie())
                .orElseThrow(() -> new ResourceNotFoundException(String.format(
                        "Movie with title %s does not exist!", dto.getMovie()
                )));
        //find existing room
        Room room = roomRepository.findByNameIgnoreCase(dto.getRoom())
                .orElseThrow(() -> new ResourceNotFoundException(String.format(
                        "Room with name %s does not exist!", dto.getRoom()
                )));

        ShowTime toSave;
        try {
            toSave = ShowTimeMapper.toEntity(dto);
        } catch (DateTimeParseException e) {
            throw new DateException(String.format(
                    "Date %s or time %s is not correct or has wrong format!",
                    dto.getDate(), dto.getStartTime()
            ));
        }

        toSave.setMovie(movie);
        toSave.setRoom(room);
        //llogarit ne ore dhe minuta filmin
        int hours = toSave.getMovie().getLength() / 60;
        int minutes = toSave.getMovie().getLength() % 60;

        String length = "0" + hours + ":" + ((minutes / 10 == 0) ? "0" + minutes % 10 : minutes);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime formattedLength = LocalTime.parse(length, formatter);
        //llogarit endTime
        LocalTime endTime = toSave.getStartTime().plusHours(formattedLength.getHour())
                .plusMinutes(formattedLength.getMinute());
        toSave.setEndTime(endTime);
        //llogarit oren kur salla tjeter eshte gati
        toSave.setReadyForNextTime(endTime.plusHours(1));

        List<ShowTime> sameRoomAndDateShowtimes = findByRoomAndDate(toSave.getRoom().getName(), toSave.getDate().toString());

        //kontroll per perplasje oraresh
        for (ShowTime sh : sameRoomAndDateShowtimes) {
            if (toSave.getReadyForNextTime().isAfter(sh.getStartTime()) ||
                    toSave.getStartTime().isAfter(sh.getStartTime())) {

                throw new HourConfusion(String.format("Ka film qe nis ne oren %s dhe salla %s eshte gati per pasardhesin ne %s!"
                        , sh.getStartTime(),sh.getRoom().getName(), sh.getReadyForNextTime()));
            }
        }

        return ShowTimeMapper.toDTO(showTimeRepository.save(toSave));
    }

    @Override
    public List<ShowTime> findByRoomAndDate(String room, String date) {
        Room roomToFind = roomRepository.findByNameIgnoreCase(room)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(
                        "Room with name %s does not exist!", room
                )));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate formattedDate = LocalDate.parse(date);

        return showTimeRepository.findByRoomAndDate(roomToFind, formattedDate);
    }
}
