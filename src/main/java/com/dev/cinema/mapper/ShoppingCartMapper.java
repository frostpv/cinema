package com.dev.cinema.mapper;

import com.dev.cinema.dto.shoppingCart.ShoppingCartResponseDto;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.Ticket;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShoppingCartMapper {
    public ShoppingCartResponseDto shoppingCartToDto(ShoppingCart shoppingCart) {
        ShoppingCartResponseDto shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setId(shoppingCart.getId());
        List<Long> ticketIds = shoppingCart.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        shoppingCartResponseDto.setTicketIds(ticketIds);
        return shoppingCartResponseDto;
    }
}
