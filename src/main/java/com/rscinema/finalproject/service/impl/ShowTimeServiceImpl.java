package com.rscinema.finalproject.service.impl;

import com.rscinema.finalproject.domain.dto.showtime.*;
import com.rscinema.finalproject.domain.entity.Movie;
import com.rscinema.finalproject.domain.entity.ShowTime;
import com.rscinema.finalproject.domain.entity.Ticket;
import com.rscinema.finalproject.domain.entity.room.Room;
import com.rscinema.finalproject.domain.exception.DateException;
import com.rscinema.finalproject.domain.exception.HourConfusion;
import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import com.rscinema.finalproject.domain.mapper.ShowTimeMapper;
import com.rscinema.finalproject.repository.MovieRepository;
import com.rscinema.finalproject.repository.RoomRepository;
import com.rscinema.finalproject.repository.ShowTimeRepository;
import com.rscinema.finalproject.repository.TicketRepository;
import com.rscinema.finalproject.service.ShowTimeService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowTimeServiceImpl implements ShowTimeService {

    private final ShowTimeRepository showTimeRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;
    private final TicketRepository ticketRepository;
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
                .startDate(dto.getDate())
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

        calculateEndDateAndTime(toSave);

        checkHourAvailability(toSave);

        //generate tickets available for this showtime
        int rows = toSave.getRoom().getRoomSize().getRow_size();
        int rowSize = toSave.getRoom().getRoomSize().getRow_num();

        List<Ticket> tickets = new ArrayList<>();

        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= rowSize; j++) {
                tickets.add(Ticket.builder()
                        .rowNumber(i)
                        .seatNumber(j)
                        .showTime(toSave)
                        .build());
            }
        }
        toSave.setTickets(tickets);

        return ShowTimeMapper.toDTO(showTimeRepository.save(toSave));
    }

    //util per create
    @Override
    public List<ShowTime> findByRoomAndDate(Integer room, LocalDate date) {
        Room roomToFind = roomRepository.findById(room)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(
                        "Room with name %s does not exist!", room
                )));

        return showTimeRepository.findByRoomAndStartDateAndDeletedIsFalseOrderByStartDateAscStartTimeAsc(roomToFind, date);
    }

    @Override
    public ShowTime findById(Integer id) {
        return showTimeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(String.format("ShowTime with id %s not found!", id))
        );
    }

    @Override
    public List<ShowTimeDTO> search(ShowTimeSearchDTO dto) {
        System.out.println(dto.getMovieId());

        return showTimeRepository.searchShowTimes(dto.getMovieId(), dto.getRoomId(), dto.getStartDate()
                        , dto.getStartTime(), dto.getEndDate(), dto.getEndTime(), dto.getDeleted()).stream()
                .map(ShowTimeMapper::toDTO)
                .toList();
    }

    @Override
    public ShowTimeDTO update(UpdateShowTimeDTO dto) {
        ShowTime showTime = ShowTimeMapper.update(findById(dto.getCurrentId()), dto);

        Movie movie = movieRepository.findById(dto.getMovieId())
                .orElseThrow(() -> new ResourceNotFoundException(String.format(
                        "No movie found with id %s", dto.getMovieId()
                )));
        showTime.setMovie(movie);
        calculateEndDateAndTime(showTime);
        checkHourAvailability(showTime);

        return ShowTimeMapper.toDTO(showTimeRepository.save(showTime));
    }

    @Transactional
    @Override
    public String delete(Integer id) {
        ShowTime showTime = findById(id);
        showTime.setDeleted(true);
        ticketRepository.updateDeleted(true,showTime.getId());
        showTimeRepository.save(showTime);
        return String.format("ShowTime with id %s deleted!", id);
    }

    @Transactional
    @Override
    public void restore(Integer id) {
        ShowTime showTime = findById(id);
        checkHourAvailability(showTime);
        showTime.setDeleted(false);
        ticketRepository.updateDeleted(false,showTime.getId());
        showTimeRepository.save(showTime);
    }

    @Override
    public ShowTimeCustomerDTO findByIdCustomer(Integer id) {
        ShowTime showTime = showTimeRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(
                        "Showtime with id %s not found!", id
                )));
        return ShowTimeCustomerDTO.builder()
                .id(showTime.getId())
                .movie(showTime.getMovie().getTitle())
                .room(showTime.getRoom().getName())
                .startDate(showTime.getStartDate())
                .startTime(showTime.getStartTime())
                .endTime(showTime.getEndTime())
                .build();
    }

    @Override
    public List<ShowTimeCustomerDTO> searchCustomerView(String movie, LocalDate date) {
        if (date != null) {
            if (!date.isAfter(LocalDate.from(LocalDateTime.now()))) {
                throw new HourConfusion("Not available date!");
            }
        }
        return showTimeRepository.searchCustomerView(movie, date)
                .stream()
                .map(sht -> ShowTimeCustomerDTO.builder()
                        .id(sht.getId())
                        .movie(sht.getMovie().getTitle())
                        .room(sht.getRoom().getName())
                        .startDate(sht.getStartDate())
                        .startTime(sht.getStartTime())
                        .endTime(sht.getEndTime())
                        .build())
                .toList();
    }

    //utility to check available hour
    private void checkHourAvailability(ShowTime toSave) {
        List<ShowTime> sameRoomAndDateShowTimes = findByRoomAndDate(toSave.getRoom().getId(), toSave.getStartDate());
        LocalDateTime startDateAndTimeOfEntity = LocalDateTime.of(toSave.getStartDate(), toSave.getStartTime());
        LocalDateTime endDateAndTimeOfEntity = LocalDateTime.of(toSave.getEndDate(), toSave.getEndTime());
        //kontrolli i ores
        //nese filmi eshte jashte orarit
        if (!startDateAndTimeOfEntity.isAfter(LocalDateTime.now())) {
            throw new HourConfusion("Confusion!");
        } else if (LocalTime.from(endDateAndTimeOfEntity).isAfter((LocalTime.parse("01:00:00"))) &&
                LocalTime.from(endDateAndTimeOfEntity).isBefore(LocalTime.parse("10:00:00"))) {
            String[] dateAndTime = endDateAndTimeOfEntity.toString().split("T");
            throw new HourConfusion(String.format("Showtime that you are creating has a movie which ends at %s, whereas the cinema closes at %s AM!"
                    , dateAndTime[1], "01:00"));

        } else if (toSave.getStartTime().isBefore(LocalTime.parse("10:00"))) {
            throw new HourConfusion("That is not a cinema hour! Cinema opens at 10:00 AM");
            //nese ska perplasje oraresh
        } else {
            for (ShowTime sh : sameRoomAndDateShowTimes) {
                if (toSave.getId() == sh.getId()) {
                    //per update
                    continue;
                }
                LocalDateTime startDateAndTimeOfTable = LocalDateTime.of(sh.getStartDate(), sh.getStartTime());

                if ((startDateAndTimeOfEntity.isBefore(startDateAndTimeOfTable)
                        && toSave.getReadyForNextTime().isAfter(startDateAndTimeOfTable) && toSave.getReadyForNextTime().isBefore(sh.getReadyForNextTime()))
                        ||
                        (startDateAndTimeOfEntity.isAfter(startDateAndTimeOfTable) && startDateAndTimeOfEntity.isBefore(sh.getReadyForNextTime())
                                && toSave.getReadyForNextTime().isAfter(startDateAndTimeOfTable))
                        ||
                        (startDateAndTimeOfEntity.equals(startDateAndTimeOfTable) || toSave.getReadyForNextTime().equals(sh.getReadyForNextTime()))
                ) {

                    throw new HourConfusion(String.format("There is a movie that starts at %s and ends at %s in room : %s " +
                                    "for the date %s!"
                            , sh.getStartTime(), sh.getEndTime(), sh.getRoom().getName(), sh.getEndDate()));
                }

            }

        }

    }

    private void calculateEndDateAndTime(ShowTime toSave) {
        //llogarit ne ore dhe minuta filmin
        int hours = toSave.getMovie().getLength() / 60;
        int minutes = toSave.getMovie().getLength() % 60;
        String length = "0" + hours + ":" + ((minutes / 10 == 0) ? "0" + minutes % 10 : minutes) + ":00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime formattedMovieLength = LocalTime.parse(length, formatter);

        //llogarit endTime sepse edhe mund te kaloje diten tjeter

        LocalDateTime endTimeAndDate = LocalDateTime.of(toSave.getStartDate(), toSave.getStartTime())
                .plusHours(formattedMovieLength.getHour())
                .plusMinutes(formattedMovieLength.getMinute());

        LocalDate endDate = LocalDate.from(endTimeAndDate);
        toSave.setEndDate(endDate);
        LocalTime endTime = LocalTime.from(endTimeAndDate);
        toSave.setEndTime(endTime);

        //llogarit oren kur salla tjeter eshte gati
        toSave.setReadyForNextTime(endTimeAndDate.plusHours(1));
    }


}
