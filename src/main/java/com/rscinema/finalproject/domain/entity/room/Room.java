package com.rscinema.finalproject.domain.entity.room;

import com.rscinema.finalproject.domain.entity.BaseEntity;
import com.rscinema.finalproject.domain.entity.ShowTime;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rooms")
public class Room extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "room_name")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type")
    private RoomSizes roomSize;

    @OneToMany(mappedBy = "room",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.PERSIST,
                    CascadeType.MERGE, CascadeType.REFRESH})
    private List<ShowTime> showTimes;


}
