package com.dev.cinema.mapper;

import com.dev.cinema.dto.order.OrderResponseDto;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {
    public OrderResponseDto orderToDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setId(order.getId());
        orderResponseDto.setOrderDate(order.getOrderDate());
        orderResponseDto.setUserId(order.getUser().getId());
        List<Long> ticketIds = order.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        orderResponseDto.setTicketIds(ticketIds);
        return orderResponseDto;
    }
}
