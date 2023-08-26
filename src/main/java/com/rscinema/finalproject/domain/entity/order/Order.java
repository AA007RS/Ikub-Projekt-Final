package com.rscinema.finalproject.domain.entity.order;

import com.rscinema.finalproject.domain.entity.BaseEntity;
import com.rscinema.finalproject.domain.entity.Ticket;
import com.rscinema.finalproject.domain.entity.user.User;
import com.rscinema.finalproject.domain.entity.payment.Payment;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    @OneToMany(mappedBy = "order", cascade = {CascadeType.REFRESH, CascadeType.MERGE})
    private List<Ticket> tickets;

    @Column(name = "total_price")
    private Double totalPrice;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "payment_id", referencedColumnName = "id", unique = true)
    private Payment payment;

    @Column(name = "closed")
    private Boolean closed;

}
