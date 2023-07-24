package com.rscinema.finalproject.domain.entity;

import com.rscinema.finalproject.domain.entity.order.Order;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "row_num")
    private Integer rowNumber;

    @Column(name = "seat_num")
    private Integer seatNumber;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "showtime_id", referencedColumnName = "id")
    private ShowTime showTime;

    @Column(name = "status")
    private boolean status;

    @ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

}
