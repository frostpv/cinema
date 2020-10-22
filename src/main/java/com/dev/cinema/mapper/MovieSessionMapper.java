package com.dev.cinema.mapper;

import com.dev.cinema.dto.session.MovieSessionRequestDto;
import com.dev.cinema.dto.session.MovieSessionResponseDto;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionMapper {
    private static final String TIME_PATTERN = "d-MM-yyyy hh:mm:ss a";
    private final MovieService movieService;
    private final CinemaHallService cinemaHallService;

    public MovieSessionMapper(MovieService movieService, CinemaHallService cinemaHallService) {
        this.movieService = movieService;
        this.cinemaHallService = cinemaHallService;
    }

    public MovieSession dtoToMovieSession(MovieSessionRequestDto requestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setMovie(movieService.getById(requestDto.getMovieId()));
        movieSession.setCinemaHall(cinemaHallService.getById(requestDto.getCinemaHallId()));
        movieSession.setShowTime(LocalDateTime.parse(requestDto.getShowTime(),
                DateTimeFormatter.ofPattern(TIME_PATTERN)));
        return movieSession;
    }

    public MovieSessionResponseDto movieSessionToDto(MovieSession movieSession) {
        MovieSessionResponseDto responseDto = new MovieSessionResponseDto();
        responseDto.setId(movieSession.getId());
        responseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        responseDto.setShowTime(movieSession.getShowTime().toString());
        responseDto.setMovieId(movieSession.getMovie().getId());
        return responseDto;
    }
}
