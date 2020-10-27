package com.dev.cinema.dto.order;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponseDto {
    private Long id;
    private List<Long> ticketIds;
    private LocalDateTime orderDate;
    private Long userId;
}
