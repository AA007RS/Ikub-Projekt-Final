package com.rscinema.finalproject.domain.entity.genre;

import com.rscinema.finalproject.domain.entity.BaseEntity;
import com.rscinema.finalproject.domain.entity.Movie;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "genres")
public class Genre extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private MovieGenre movieGenre;

    @OneToMany(mappedBy = "genre",cascade = CascadeType.ALL)
    private List<Movie> movies;

    @Override
    public Integer getId() {
        return id;
    }
}
