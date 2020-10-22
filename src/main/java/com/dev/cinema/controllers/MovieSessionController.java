package com.dev.cinema.controllers;

import com.dev.cinema.dto.session.MovieSessionRequestDto;
import com.dev.cinema.dto.session.MovieSessionResponseDto;
import com.dev.cinema.mapper.MovieSessionMapper;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.service.MovieSessionService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie-sessions")
public class MovieSessionController {
    private static final String DATE_FORMAT = "d.MM.yyyy";
    private final MovieSessionService movieSessionService;
    private final MovieSessionMapper movieSessionMapper;

    public MovieSessionController(MovieSessionService service, MovieSessionMapper mapper) {
        this.movieSessionService = service;
        this.movieSessionMapper = mapper;
    }

    @PostMapping("/")
    public void addMovieSession(@RequestBody MovieSessionRequestDto requestDto) {
        movieSessionService.add(movieSessionMapper.dtoToMovieSession(requestDto));
    }

    @GetMapping("/available")
    public List<MovieSessionResponseDto> getAllAvailable(@RequestParam Long movieId,
                                                         @RequestParam String date) {
        List<MovieSession> movieSessions =
                movieSessionService.findAvailableSessions(movieId,
                        LocalDate.parse(date, DateTimeFormatter.ofPattern(DATE_FORMAT)));

        return movieSessions.stream()
                .map(movieSessionMapper::movieSessionToDto)
                .collect(Collectors.toList());
    }
}
