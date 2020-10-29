package com.dev.cinema.dto.movie;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class MovieRequestDto {
    @NotNull
    private String title;
    @NotNull
    private String description;
}
