package com.dev.cinema.dto.session;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class MovieSessionRequestDto {
    @NotNull
    private Long cinemaHallId;
    @NotNull
    private String showTime;

}
