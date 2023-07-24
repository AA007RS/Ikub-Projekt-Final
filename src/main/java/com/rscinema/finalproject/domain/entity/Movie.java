package com.rscinema.finalproject.domain.entity;

import com.rscinema.finalproject.domain.entity.genre.Genre;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movies")
public class Movie extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "director")
    private String director;

    @Column(name = "year_released")
    private Integer yearReleased;

    @Column(name = "length")
    private Integer length;

    @Column(name = "description")
    private String description  ;

    @OneToMany(mappedBy = "movie",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private List<ShowTime> showTimes;


    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE})
    @JoinColumn(name = "genre_id",referencedColumnName = "id")
    private Genre genre;

    @Override
    public Integer getId() {
        return id;
    }
}
