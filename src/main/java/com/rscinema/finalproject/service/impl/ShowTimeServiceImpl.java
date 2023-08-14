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
                    "Date or time %s is not correct or has wrong format!",
                    dto.getStartTime()
            ));
        }
        toSave.setMovie(movie);
        toSave.setRoom(room);

        //llogarit ne ore dhe minuta filmin
        int hours = toSave.getMovie().getLength() / 60;
        int minutes = toSave.getMovie().getLength() % 60;
        String length = "0" + hours + ":" + ((minutes / 10 == 0) ? "0" + minutes % 10 : minutes) + ":00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime formattedMovieLength = LocalTime.parse(length, formatter);

        //llogarit endTime
        LocalDateTime endTimeAndDate = LocalDateTime.of(toSave.getDate(),toSave.getStartTime())
                .plusHours(formattedMovieLength.getHour())
                .plusMinutes(formattedMovieLength.getMinute());
        toSave.setEndTime(endTimeAndDate);

        //llogarit oren kur salla tjeter eshte gati
        toSave.setReadyForNextTime(endTimeAndDate.plusHours(1));

        List<ShowTime> sameRoomAndDateShowTimes = findByRoomAndDate(toSave.getRoom().getId(),toSave.getDate());

        LocalDateTime startDateAndTimeOfEntity = LocalDateTime.of(toSave.getDate(),toSave.getStartTime());
        //kontrolli i ores
        for (ShowTime sh : sameRoomAndDateShowTimes) {
            LocalDateTime startDateAndTimeOfTable = LocalDateTime.of(sh.getDate(),sh.getStartTime());
            LocalDate theNextDay = sh.getDate().plusDays(1);

            //nese filmi eshte jashte orarit
             if (toSave.getEndTime().isAfter(LocalDateTime.of(theNextDay,LocalTime.parse("01:00:00")))) {
                 String[] dateAndTime = toSave.getEndTime().toString().split("T");
                throw new HourConfusion(String.format("Showtime that you are creating has a movie which ends at %s, whereas the cinema closes at %s AM!"
                        ,dateAndTime[1] , "01:00"));

            }else if (toSave.getStartTime().isBefore(LocalTime.parse("10:00"))) {
                 throw new HourConfusion("That is not a cinema hour! Cinema opens at 10:00 AM");
                 //nese ska perplasje oraresh
             }
            else if ((startDateAndTimeOfEntity.isBefore(startDateAndTimeOfTable)
                    && toSave.getReadyForNextTime().isAfter(startDateAndTimeOfTable) && toSave.getReadyForNextTime().isBefore(sh.getReadyForNextTime()))
                        ||
                    (startDateAndTimeOfEntity.isAfter(startDateAndTimeOfTable) && startDateAndTimeOfEntity.isBefore(sh.getReadyForNextTime())
                            && toSave.getReadyForNextTime().isAfter(startDateAndTimeOfTable) )
                    ||
                    (startDateAndTimeOfEntity.equals(startDateAndTimeOfTable)||toSave.getReadyForNextTime().equals(sh.getReadyForNextTime()))
                    ){
                 String[] dateAndTime = sh.getEndTime().toString().split("T");
                throw new HourConfusion(String.format("There is a movie that starts at %s and ends at %s in room : %s!"
                        , sh.getStartTime(), dateAndTime[1], sh.getRoom().getName()));
            }
        }
        return ShowTimeMapper.toDTO(showTimeRepository.save(toSave));
    }

    @Override
    public List<ShowTime> findByRoomAndDate(Integer room, LocalDate date) {
        Room roomToFind = roomRepository.findById(room)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(
                        "Room with name %s does not exist!", room
                )));

        return showTimeRepository.findByRoomAndDateOrderByDateAscStartTimeAsc(roomToFind, date);
    }

    @Override
    public List<ShowTimeDTO> findByDate(String date) {
        LocalDate formattedDate = LocalDate.parse(date);

        return showTimeRepository.findByDateOrderByStartTime(formattedDate).stream()
                .map(ShowTimeMapper::toDTO)
                .toList();
    }
}
