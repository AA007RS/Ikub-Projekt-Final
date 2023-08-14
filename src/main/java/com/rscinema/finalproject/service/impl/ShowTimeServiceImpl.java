package com.rscinema.finalproject.service.impl;

import com.rscinema.finalproject.domain.dto.showtime.RegisterShowTimeDTO;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.DateTime;

@Service
@RequiredArgsConstructor
public class ShowTimeServiceImpl implements ShowTimeService {

    private final ShowTimeRepository showTimeRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;

    @Override
    public ShowTimeDTO create(RegisterShowTimeDTO dto) {
        //find existing movie
        Movie movie = movieRepository.findById(dto.getMovie())
                .orElseThrow(() -> new ResourceNotFoundException(String.format(
                        "Movie with id %s does not exist!", dto.getMovie()
                )));
        //find existing room
        Room room = roomRepository.findById(dto.getRoom())
                .orElseThrow(() -> new ResourceNotFoundException(String.format(
                        "Room with name %s does not exist!", dto.getRoom()
                )));
        // create converter dto
        ShowTimeDTO showTimeDTO = ShowTimeDTO.builder()
                .movie(movie.getTitle())
                .room(room.getName())
                .date(dto.getDate())
                .startTime(dto.getStartTime())
                .price(dto.getPrice())
                .build();

        ShowTime toSave;
        try {
            toSave = ShowTimeMapper.toEntity(showTimeDTO);
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
        String length = "0" + hours + ":" + ((minutes / 10 == 0) ? "0" + minutes % 10 : minutes) + ":00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime formattedLength = LocalTime.parse(length, formatter);

        //llogarit endTime
        LocalTime endTime = toSave.getStartTime().plusHours(formattedLength.getHour())
                .plusMinutes(formattedLength.getMinute());
        toSave.setEndTime(endTime);

        //llogarit oren kur salla tjeter eshte gati
        toSave.setReadyForNextTime(endTime.plusHours(1));

        List<ShowTime> sameRoomAndDateShowTimes = findByRoomAndDate(toSave.getRoom().getName(), toSave.getDate().toString());
        System.out.println(toSave.getEndTime());
        //kontrolli i ores
        for (ShowTime sh : sameRoomAndDateShowTimes) {
            //nese filmi eshte jashte orarit
            if (toSave.getStartTime().isBefore(LocalTime.parse("10:00"))
                    ||
                    (!toSave.getEndTime().isBefore(LocalTime.parse("01:00"))
                            &&
                            toSave.getEndTime().isBefore(LocalTime.parse("04:00")))){
                throw new HourConfusion("Kujdes orarin! Kinemaja hapet ne oren 10:00 dhe mbyllet ne 01:00");
            //nese ska perplasje oraresh
            } else if ((toSave.getReadyForNextTime().isAfter(sh.getStartTime()) && toSave.getReadyForNextTime().isBefore(sh.getReadyForNextTime()))
                    ||
                    toSave.getStartTime().isAfter(sh.getStartTime()) && toSave.getStartTime().isBefore(sh.getReadyForNextTime())
                    ||
                    toSave.getStartTime().equals(sh.getStartTime()) || toSave.getReadyForNextTime().equals(sh.getReadyForNextTime())) {

                throw new HourConfusion(String.format("Ka film qe nis ne oren %s dhe perfundon ne %s ne sallen %s!"
                        , sh.getStartTime(), sh.getEndTime(), sh.getRoom().getName()));
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
        LocalDate formattedDate = LocalDate.parse(date);

        return showTimeRepository.findByRoomAndDate(roomToFind, formattedDate);
    }

    @Override
    public List<ShowTimeDTO> findByDate(String date) {
        LocalDate formattedDate = LocalDate.parse(date);

        return showTimeRepository.findByDateOrderByStartTime(formattedDate).stream()
                .map(ShowTimeMapper::toDTO)
                .toList();
    }
}
