package com.rscinema.finalproject.domain.entity;

import com.rscinema.finalproject.domain.entity.room.Room;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "showtimes")
public class ShowTime extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @Column(name = "startDate")
    private LocalDate startDate;

    @Column(name = "startTime")
    private LocalTime startTime;

    @Column(name = "endDate")
    private LocalDate endDate;

    @Column(name = "endTime")
    private LocalTime endTime;

    @Column(name = "ready_for_next")
    private LocalDateTime readyForNextTime;

    @Column(name = "price")
    private Double price;

    @OneToMany(mappedBy = "showTime", cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}