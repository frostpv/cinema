package com.dev.cinema.dto.session;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovieSessionRequestDto {
    private Long movieId;
    @NotNull
    private Long cinemaHallId;
    @NotNull
    private String showTime;

}
