package com.rscinema.finalproject.domain.entity;

import com.rscinema.finalproject.domain.entity.room.Room;
import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "movie_id", referencedColumnName = "id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,
            CascadeType.REFRESH})
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "price")
    private Double price;


    @OneToMany(mappedBy = "showTime", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

}
