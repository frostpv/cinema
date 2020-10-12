package com.dev.cinema.service;

import com.dev.cinema.lib.Service;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        return null;
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return null;
    }
}
