package com.dev.cinema.dto.session;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class MovieSessionRequestDto {
    private Long movieId;
    @NotNull
    private Long cinemaHallId;
    @NotNull
    private String showTime;

}
