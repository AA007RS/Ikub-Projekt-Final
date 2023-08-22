package com.rscinema.finalproject.service.impl;

import com.rscinema.finalproject.configuration.SecurityUtils;
import com.rscinema.finalproject.domain.dto.OrderDTO;
import com.rscinema.finalproject.domain.entity.Ticket;
import com.rscinema.finalproject.domain.entity.order.Order;
import com.rscinema.finalproject.domain.entity.payment.Payment;
import com.rscinema.finalproject.domain.entity.user.User;
import com.rscinema.finalproject.domain.exception.ResourceNotFoundException;
import com.rscinema.finalproject.domain.mapper.OrderMapper;
import com.rscinema.finalproject.repository.OrderRepository;
import com.rscinema.finalproject.repository.TicketRepository;
import com.rscinema.finalproject.repository.UserRepository;
import com.rscinema.finalproject.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Override
    public OrderDTO findById(Integer id) {
        return

        OrderMapper.toDTO(orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Ckemi")
        ));
    }

    @Override
    public OrderDTO addTicket(Integer ticketId) {

        User loggedUser = userRepository.findById(SecurityUtils.getLoggedUserId()).orElseThrow(
                () -> new ResourceNotFoundException("User not found"));
        Ticket toAdd = ticketRepository.findByIdAndReservedIsFalse(ticketId).orElseThrow(
                () -> new ResourceNotFoundException(String.format(
                        "Ticket with id %s already reserved!",ticketId
                )));
        Order order = null;

        if(orderRepository.findByUserAndClosedIsFalse(loggedUser).isEmpty()){
            order = new Order();
            order.setUser(loggedUser);
            order.setTotalPrice(toAdd.getShowTime().getPrice());
            order.setTickets(new ArrayList<>());
            order.getTickets().add(toAdd);
            Payment payment = new Payment();
            payment.setOrder(order);
            order.setPayment(payment);
            order.setClosed(false);

        }else {
            order = orderRepository.findByUserAndClosedIsFalse(loggedUser).orElseThrow(
                    ()-> new ResourceNotFoundException(
                            "Order status closed not found!"
                    )
            );

           // order.getTickets().add(toAdd);
            order.setTotalPrice(order.getTotalPrice() + toAdd.getShowTime().getPrice());

        }
        toAdd.setOrder(order);
        toAdd.setReserved(true);
        orderRepository.save(order);
        return OrderMapper.toDTO(order);
    }

    @Override
    public OrderDTO removeTicket(Integer ticketId) {
        User loggedUser = userRepository.findById(SecurityUtils.getLoggedUserId()).orElseThrow(
                () -> new ResourceNotFoundException("User not found"));
        Order order = orderRepository.findByUserAndClosedIsFalse(loggedUser).orElseThrow(
                ()-> new ResourceNotFoundException(
                        "Order status closed not found!"
                )
        );
        Ticket toRemove = ticketRepository.findById(ticketId).orElseThrow(
                () -> new ResourceNotFoundException(String.format(
                        "Ticket with id %s not found",ticketId
                )));
        toRemove.setOrder(null);
        toRemove.setReserved(false);
        if(order.getTotalPrice()-toRemove.getShowTime().getPrice()==0){
            orderRepository.delete(order);
            return null;
        }else{
            order.setTotalPrice(order.getTotalPrice()-toRemove.getShowTime().getPrice());
        }
        ticketRepository.save(toRemove);
        return OrderMapper.toDTO(order);
    }

    @Override
    public void delete(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Not found!")
        );
        for (Ticket t: order.getTickets()){
            t.setOrder(null);
            t.setReserved(false);
            ticketRepository.save(t);
        }
        orderRepository.delete(order);
    }
}
